module NETJLO_core {
    requires NETJLO_base;

    exports ch.giuntini.netjlo_core.connections.client;
    exports ch.giuntini.netjlo_core.connections.server.multiple;
    exports ch.giuntini.netjlo_core.connections.server.single;
    exports ch.giuntini.netjlo_core.interpreter;
    exports ch.giuntini.netjlo_core.packages;
    exports ch.giuntini.netjlo_core.socket;
    exports ch.giuntini.netjlo_core.streams;
    exports ch.giuntini.netjlo_core.threads;
}