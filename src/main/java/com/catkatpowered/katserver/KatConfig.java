package com.catkatpowered.katserver;

import com.catkatpowered.katserver.common.KatMiscConstants;
import com.catkatpowered.katserver.log.KatLogger;
import com.catkatpowered.katserver.util.KatWorkingDir;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import lombok.Getter;
import org.yaml.snakeyaml.Yaml;

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
                            .getResourceAsStream("config.yml"))
                        .readAllBytes());
                    outputStream.flush();
                    outputStream.close();

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

                    if (!Files.exists(Path.of(KatWorkingDir.fixPath(katDataFolderPath))))
                        Files.createDirectory(Path.of(KatWorkingDir.fixPath(katDataFolderPath)));

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
