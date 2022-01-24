package com.catkatpowered.katserver.config;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.common.constants.KatMiscConstants;
import com.catkatpowered.katserver.common.utils.KatWorkingDir;
import com.catkatpowered.katserver.log.KatLoggerManager;
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
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

/**
 * Kat Server 的配置文件项
 *
 * @author CatMoe
 * @author suibing112233
 */
public class KatConfig {

    private static final KatConfig Instance = new KatConfig();

    private final Logger logger = KatServer.KatLoggerAPI
        .getLogger(KatMiscConstants.KAT_PROJECT_NAME);

    @Getter
    private Integer katNetworkPort = 25565;
    @Getter
    private String katDataFolderPath = "";
    @Getter
    private Map<String, Object> configContent;

    private KatConfig() {
        File katConfigFile = new File(KatWorkingDir.fixPath("./config.yml"));
        try {
            // 检测配置文件状态并处理
            if (katConfigFile.exists()) {
                Yaml yaml = new Yaml();
                InputStream inputStream = new FileInputStream(katConfigFile);
                configContent = yaml.load(inputStream);
                inputStream.close();
                // 开始获取常量
                katNetworkPort = Integer.parseInt(configContent.get("network_port").toString());
                katDataFolderPath = configContent.get("data_folder_path").toString();
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
                    configContent = yaml.load(inputStream);
                    inputStream.close();
                    // 开始获取常量
                    katNetworkPort = Integer.parseInt(configContent.get("network_port").toString());
                    katDataFolderPath = configContent.get("data_folder_path").toString();
                } else {
                    logger.fatal("Unable to write config file!");
                }
            }
            // 检测数据储存文件夹状态并处理
            if (!Files.exists(Path.of(KatWorkingDir.fixPath(katDataFolderPath)))) {
                Files.createDirectory(Path.of(KatWorkingDir.fixPath(katDataFolderPath)));
            }

        } catch (Exception e) {
            KatLoggerManager.getLogger().fatal(e.getStackTrace());
        }
    }

    public static KatConfig getInstance() {
        return Instance;
    }
}
