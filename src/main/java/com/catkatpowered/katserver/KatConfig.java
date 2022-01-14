package com.catkatpowered.katserver;


import com.catkatpowered.katserver.log.KatLogger;
import org.apache.logging.log4j.Level;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;
import java.util.Objects;

public class KatConfig {
    public static Integer KAT_NETWORK_PORT = 25565; // ;)

    public static void KatConfigMain() {
        File katConfigFile = new File("./config.yml");
        if (katConfigFile.exists()) {
            Yaml yaml = new Yaml();
            try {
                InputStream inputStream = new FileInputStream(katConfigFile);
                Map< String, Object> config = yaml.load(inputStream);
                inputStream.close();
                // 开始获取常量
                KAT_NETWORK_PORT = Integer.parseInt(config.get("network_port").toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (katConfigFile.createNewFile()) {
                    OutputStream outputStream = new FileOutputStream(katConfigFile);
                    outputStream.write(Objects.requireNonNull(KatConfig.class
                                    .getClassLoader()
                                    .getResourceAsStream("config.yml"))
                            .readAllBytes());
                    outputStream.flush();
                    outputStream.close();
                } else {
                    KatLogger.getInstance().fatal("Unable to write config file!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
