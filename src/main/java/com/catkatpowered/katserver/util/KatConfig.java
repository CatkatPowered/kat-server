package com.catkatpowered.katserver.util;

import com.catkatpowered.katserver.api.KatLoggerManager;
import com.catkatpowered.katserver.common.KatMiscConstants;
import lombok.Getter;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;

/**
 * Kat Server 的配置文件项
 *
 * @author CatMoe
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
        try {
            // 检测配置文件状态并处理
            if (katConfigFile.exists()) {
                Yaml yaml = new Yaml();
                InputStream inputStream = new FileInputStream(katConfigFile);
                Map<String, Object> yamlContent = yaml.load(inputStream);
                inputStream.close();
                // 开始获取常量
                katNetworkPort = Integer.parseInt(yamlContent.get("network_port").toString());
                katDataFolderPath = yamlContent.get("data_folder_path").toString();
            } else {
                if (katConfigFile.createNewFile()) {
                    OutputStream outputStream = new FileOutputStream(katConfigFile);
                    outputStream.write(Objects.requireNonNull(KatConfig.class
                                    .getClassLoader()
                                    .getResourceAsStream("config.yml"))
                            .readAllBytes());
                    outputStream.flush();
                    outputStream.close();

                    Yaml yaml = new Yaml();
                    InputStream inputStream = new FileInputStream(katConfigFile);
                    Map<String, Object> yamlContent = yaml.load(inputStream);
                    inputStream.close();
                    // 开始获取常量
                    katNetworkPort = Integer.parseInt(yamlContent.get("network_port").toString());
                    katDataFolderPath = yamlContent.get("data_folder_path").toString();
                } else {
                    KatLoggerManager
                            .getLogger(KatMiscConstants.KAT_PROJECT_NAME)
                            .fatal("Unable to write config file!");
                }
            }
            // 检测数据储存文件夹状态并处理
            if (!Files.exists(Path.of(KatWorkingDir.fixPath(katDataFolderPath))))
                Files.createDirectory(Path.of(KatWorkingDir.fixPath(katDataFolderPath)));

        } catch (Exception e) {
            KatLoggerManager.getLogger().fatal(e.getStackTrace());
        }
    }

    public static KatConfig getInstance() {
        return Instance;
    }
}
