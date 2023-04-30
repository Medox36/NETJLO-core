package ch.giuntini.netjlo_core.connections.server.multiple;

import ch.giuntini.netjlo_base.connections.client.sockets.BaseSocket;
import ch.giuntini.netjlo_base.connections.server.sockets.CustomServerSocket;
import ch.giuntini.netjlo_base.packages.BasePackage;
import ch.giuntini.netjlo_core.interpreter.Interpretable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Prototype
 * <p>Use with caution
 */
//TODO try to fix this generic mess
@Deprecated
public class MultipleServerConnectionHolder {

    @SuppressWarnings("rawtypes")
    private static final List<MultipleServerConnection> INSTANCES = new ArrayList<>(0);

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static
    <T extends CustomServerSocket<S>, S extends BaseSocket, P extends BasePackage, I extends Interpretable<P>>
    MultipleServerConnection<T, S, P, I>
    getInstance(Class<T> serverSocketC, Class<S> socketC, Class<P> packC, Class<I> interpreterC) {
        synchronized (INSTANCES) {
            Class<?>[] array = new Class[]{serverSocketC, socketC, packC, interpreterC};
            for (MultipleServerConnection connection : INSTANCES) {
                if (Arrays.equals(connection.getTypes(), array)) {
                    return connection;
                }
            }
            throw new NoSuchElementException("There is no MultipleServerConnection " +
                    "with the given generic types/classes ");
        }
    }

    @SuppressWarnings("rawtypes")
    private static
    <T extends CustomServerSocket<S>, S extends BaseSocket, P extends BasePackage, I extends Interpretable<P>>
    boolean
    checkInstance(MultipleServerConnection<T, S, P, I> connection) {
        synchronized (INSTANCES) {
            Class<?>[] arr = connection.getTypes();
            for (MultipleServerConnection con : INSTANCES) {
                if (Arrays.equals(con.getTypes(), arr)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static
    <T extends CustomServerSocket<S>, S extends BaseSocket, P extends BasePackage, I extends Interpretable<P>>
    void
    addInstance(MultipleServerConnection<T, S, P, I> connection) {
        synchronized (INSTANCES) {
            if (checkInstance(connection))
                throw new IllegalStateException("An Instance of a MultipleServerConnection " +
                        "with the given generic types already exists");
            INSTANCES.add(connection);
        }
    }
}
