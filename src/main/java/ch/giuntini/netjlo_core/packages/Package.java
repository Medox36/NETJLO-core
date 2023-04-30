package ch.giuntini.netjlo_core.packages;

import ch.giuntini.netjlo_base.packages.BasePackage;

public class Package extends BasePackage {
    public final String serverPrefix;
    public final String prefix;
    public final String uuid;

    public Package(String serverPrefix, String prefix, String information, String uuid) {
        super(information);
        this.serverPrefix = serverPrefix;
        this.prefix = prefix;
        this.uuid = uuid;
    }
}
