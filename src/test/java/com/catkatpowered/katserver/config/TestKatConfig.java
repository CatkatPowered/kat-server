package com.catkatpowered.katserver.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.common.constants.KatConfigNodeConstants;

public class TestKatConfig {
  String defaultConfig = """
      ######################### Network #############################
      network:
        network_port: 25565
        selfgen_cert:
          cert_password: "catmoe"
          cert_alias: "catmoe"
        custom_cert:
          enabled: false
          cert_path: "./cert.jks"
          cert_password: "catmoe"

      ######################### Database ############################
      database:
        # default support mongodb
        # demo database_url:
        # mongodb: mongodb://localhost:27017/database_name
        #
        # Note: No need to carry username and password in url
        database_url: mongodb://localhost:27017/kat-server
        database_username:
        database_password:

      ####################### Storage ###############################
      # The resource file storage
      resource:
        # You can choose those type of storage_provider
        # 1.local
        storage_provider: local

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
        #
        # Unit: Millisecond
        outdated: 10000

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

    var nullConfigNode = KatServer.KatConfigAPI.getConfig(null); // 炒饭
    assertEquals(Optional.empty(), nullConfigNode);

    // Try worn type cast
    KatServer.KatConfigAPI.<Test>getConfig(KatConfigNodeConstants.KAT_CONFIG_DATABASE); // 炒饭，酒吧没炸就行

    // Get already exist config node
    var network = KatServer.KatConfigAPI.<Map<String, Object>>getConfig(KatConfigNodeConstants.KAT_CONFIG_NETWORK);
    assertEquals(25565, network.get().get("network_port"));

    var networkPort = KatServer.KatConfigAPI.<Integer>getConfig(KatConfigNodeConstants.KAT_CONFIG_NETWORK_PORT);
    assertEquals(25565, networkPort.get());
  }
}
