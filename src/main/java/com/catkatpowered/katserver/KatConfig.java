package com.catkatpowered.katserver;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;

public class KatConfig {
    public static Integer KatNetworkPort = 25565; // ;)

    public static void KatConfigLoad() {
        Config KatConfig = ConfigFactory.parseFile(new File("./config.yml"));
        KatNetworkPort = KatConfig.getInt("networkport");

    }
}
