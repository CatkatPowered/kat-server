package com.catkatpowered.katserver.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.Optional;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.common.constants.KatConfigNodeConstants;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

public class TestKatConfig {
    String defaultConfig = """
                ######################### Network #############################
                network:
                  network_port: 25565

                ######################### Database ############################
                database:
                  # The SQLite only for development!
                  #
                  # You can choose those type of database:
                  # 1. postgresql
                  # 2. mysql
                  # 3. mongodb
                  # 4. sqlite
                  database_type: sqlite

                  # If you choose sqlite, you don't have to add anything auth.
                  # Twice, it only for development!
                  # Use mysql postgresql or mongodb you need to add auth.
                  #
                  # demo database_url:
                  # postgresql: postgresql://localhost:5432/database_name
                  # mysql: mysql://localhost:3306/database_name
                  # mongodb: mongodb://localhost:27017/database_name
                  #
                  # Note: No need to carry username and password in url
                  database_url:
                  database_username:
                  database_password:

                ####################### Storage ###############################
                # The resource file storage
                resource:
                  # You can choose those type of resource_storage
                  # 1.local
                  resource_storage: local

                  # If you choose `local`,
                  # you can set the resource file where to store.
                  data_folder_path: "./data"

                ####################### ExecThreads ############################
                exec:
                  exec_threads: 16

                ####################### TokenPool ##############################
                # This section are the settings of token pool

                tokenpool:
                  # You can set the token how long to live
                  outdate: 10
            """;

    @Test
    public void testConfigNode() {
        Map<String, Object> yml = new Yaml().load(defaultConfig);
        KatConfig.getInstance().setConfigContent(yml);

        // Get undefined or null config node
        var undefinedNode = KatServer.KatConfigAPI.getConfig("undefined");
        assertEquals(Optional.empty(), undefinedNode);

        var nullNode = KatServer.KatConfigAPI.getConfig("");
        assertEquals(Optional.empty(), nullNode);

        var nullConfigNode = KatServer.KatConfigAPI.getConfig(null);
        assertEquals(Optional.empty(), nullConfigNode);

        // Get already exist config node
        var network = KatServer.KatConfigAPI.<Map<String, Object>>getConfig(KatConfigNodeConstants.KAT_CONFIG_NETWORK);
        assertEquals(25565, network.get().get("network_port"));

        var networkPort = KatServer.KatConfigAPI.<Integer>getConfig(KatConfigNodeConstants.KAT_CONFIG_NETWORK_PORT);
        assertEquals(25565, networkPort.get());
    }
}
