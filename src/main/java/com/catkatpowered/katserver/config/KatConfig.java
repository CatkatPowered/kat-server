package com.catkatpowered.katserver.config;

import com.catkatpowered.katserver.common.constants.KatConfigNodeConstants;
import com.catkatpowered.katserver.common.utils.KatWorkingDir;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
@Slf4j
public class KatConfig {

    private static final KatConfig Instance = new KatConfig();

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

                } else {
                    log.error("Unable to write config file!");
                }
            }

            // 开始获取常量
            katNetworkPort = Integer.parseInt(
                    configContent.get(KatConfigNodeConstants.KAT_CONFIG_NETWORK_PORT).toString());
            katDataFolderPath = configContent.get(
                    KatConfigNodeConstants.KAT_CONFIG_DATA_FOLDER_PATH).toString();

            // 检测数据储存文件夹状态并处理
            if (!Files.exists(Path.of(KatWorkingDir.fixPath(katDataFolderPath)))) {
                Files.createDirectory(Path.of(KatWorkingDir.fixPath(katDataFolderPath)));
            }

        } catch (Exception e) {
            log.error(String.valueOf(e));
        }
    }

    public static KatConfig getInstance() {
        return Instance;
    }
}
