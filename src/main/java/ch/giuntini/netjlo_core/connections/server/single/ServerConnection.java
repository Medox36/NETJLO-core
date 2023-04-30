package ch.giuntini.netjlo_core.connections.server.single;

import ch.giuntini.netjlo_base.connections.client.sockets.BaseSocket;
import ch.giuntini.netjlo_base.connections.server.Acceptable;
import ch.giuntini.netjlo_base.connections.server.sockets.CustomServerSocket;
import ch.giuntini.netjlo_base.packages.BasePackage;
import ch.giuntini.netjlo_base.socket.Disconnectable;
import ch.giuntini.netjlo_core.connections.client.Connection;
import ch.giuntini.netjlo_core.interpreter.Interpretable;
import ch.giuntini.netjlo_core.socket.Send;

import java.io.IOException;

public class ServerConnection
        <T extends CustomServerSocket<S>, S extends BaseSocket, P extends BasePackage, I extends Interpretable<P>>
        implements Acceptable, Disconnectable, Send<P> {

    private Connection<S, P, I> connection;
    protected final T serverSocket;
    protected final Class<P> packC;
    protected final Class<I> interpreterC;

    public ServerConnection(T serverSocket, Class<I> interpreterC, Class<P> packC) {
        this.serverSocket = serverSocket;
        this.interpreterC = interpreterC;
        this.packC = packC;
    }

    @Override
    public void acceptAndWait() throws IOException {
        S socket = serverSocket.accept();
        connection = new Connection<>(socket, interpreterC, packC);
    }

    @Override
    public void disconnect() throws IOException {
        connection.disconnect();
        serverSocket.close();
    }

    @Override
    public void send(P pack) {
        connection.send(pack);
    }
}
