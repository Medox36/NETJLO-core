package ch.giuntini.netjlo_core.socket;

import ch.giuntini.netjlo_core.packages.BasePackage;

public interface Send<P extends BasePackage<?>> {

    default void send(P p) {
        // TODO set proper Exception message
        throw new UnsupportedOperationException("");
    }

    default void sendToAll(P p) {
        // TODO set proper Exception message
        throw new UnsupportedOperationException("");
    }
}
