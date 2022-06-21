package com.catkatpowered.katserver.config;

import com.catkatpowered.katserver.common.utils.KatWorkSpace;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;
import java.util.Objects;

/**
 * KatServer 的配置文件项
 *
 * @author CatMoe
 * @author Krysztal
 */
@Slf4j
public class KatConfig {

    private static final KatConfig Instance = new KatConfig();

    @Getter
    @Setter
    private Map<String, Object> configContent;

    private KatConfig() {
        File katConfigFile = new File(KatWorkSpace.fixPath("./config.yml"));
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
                    outputStream.write(Objects.requireNonNull(KatConfig.class.getClassLoader().getResourceAsStream("config.yml")).readAllBytes());
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

        } catch (Exception e) {
            log.error(String.valueOf(e));
        }
    }

    public static KatConfig getInstance() {
        return Instance;
    }
}
