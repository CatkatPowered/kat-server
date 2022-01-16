package com.catkatpowered.katserver;

import com.catkatpowered.katserver.common.KatMiscConstants;
import com.catkatpowered.katserver.log.KatLogger;
import com.catkatpowered.katserver.util.KatWorkingDir;
import org.yaml.snakeyaml.Yaml;

import lombok.Getter;

import java.io.*;
import java.util.Map;
import java.util.Objects;

/**
 * Kat Server 的配置文件项
 *
 * @author exusiai
 * @author suibing112233
 */
public class KatConfig {

    private static final KatConfig Instance = new KatConfig();

    @Getter
    private Integer katNetworkPort = 25565;
    @Getter
    private String katDataFolderPath = "";

    private KatConfig() {
        File katConfigFile = new File(KatWorkingDir.fixPath("./config.yml"));
        if (katConfigFile.exists()) {
            Yaml yaml = new Yaml();
            try {
                InputStream inputStream = new FileInputStream(katConfigFile);
                Map<String, Object> yamlContent = yaml.load(inputStream);
                inputStream.close();
                // 开始获取常量
                katNetworkPort = Integer.parseInt(yamlContent.get("network_port").toString());
                katDataFolderPath = yamlContent.get("data_folder_path").toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (katConfigFile.createNewFile()) {
                    OutputStream outputStream = new FileOutputStream(katConfigFile);
                    outputStream.write(Objects.requireNonNull(KatConfig.class
                            .getClassLoader()
                            .getResourceAsStream(KatWorkingDir.fixPath("./config.yml")))
                        .readAllBytes());
                    outputStream.flush();
                    outputStream.close();
                } else {
                    KatLogger
                        .getLogger(KatMiscConstants.KAT_PROJECT_NAME)
                        .fatal("Unable to write config file!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static KatConfig getInstance() {
        return Instance;
    }
}
