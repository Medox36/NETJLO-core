package ch.giuntini.netjlo_core.connections.client;

import ch.giuntini.netjlo_base.socket.Connectable;
import ch.giuntini.netjlo_base.socket.Disconnectable;
import ch.giuntini.netjlo_base.connections.client.sockets.BaseSocket;
import ch.giuntini.netjlo_base.packages.BasePackage;
import ch.giuntini.netjlo_core.interpreter.Interpretable;
import ch.giuntini.netjlo_core.socket.Send;
import ch.giuntini.netjlo_core.threads.ReceiverThread;
import ch.giuntini.netjlo_core.threads.SenderThread;

import java.io.IOException;

public class Connection<S extends BaseSocket, P extends BasePackage, I extends Interpretable<P>>
        implements Connectable, Disconnectable, Send<P> {

    protected S socket;
    private SenderThread<S, P, I> senderThread;
    private ReceiverThread<S, P, I> receiverThread;

    protected Connection() {
    }

    public Connection(S socket, Class<I> interpreterC, Class<P> packC) {
        this.socket = socket;
        senderThread = new SenderThread<>(this, socket);
        receiverThread = new ReceiverThread<>(this, socket, interpreterC, packC);
    }

    @Override
    public void connect() throws IOException {
        socket.connect();
        receiverThread.start();
        senderThread.start();
    }

    @Override
    public void disconnect() throws IOException {
        if (!socket.isClosed()) {
            senderThread.close();
            receiverThread.close();
            socket.disconnect();
        }
    }

    @Override
    public void send(P pack) {
        senderThread.addPackageToSendStack(pack);
    }
}
