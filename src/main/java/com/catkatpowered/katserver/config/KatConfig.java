package com.catkatpowered.katserver.config;

import com.catkatpowered.katserver.common.utils.KatWorkSpace;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.tomlj.Toml;
import org.tomlj.TomlParseResult;

import java.io.*;
import java.util.HashMap;
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
    private Map<String, Object> configContent = new HashMap<>();

    private KatConfig() {
        File katConfigFile = new File(KatWorkSpace.fixPath("./config.toml"));
        try {
            // 检测配置文件状态并处理
            if (katConfigFile.exists()) {
                InputStream inputStream = new FileInputStream(katConfigFile);
                TomlParseResult toml = Toml.parse(inputStream);
                for (Map.Entry<String, Object> entry : toml.dottedEntrySet()) {
                    configContent.put(entry.getKey(), entry.getValue());
                }
                inputStream.close();

            } else {
                if (katConfigFile.createNewFile()) {
                    OutputStream outputStream = new FileOutputStream(katConfigFile);
                    outputStream.write(Objects.requireNonNull(KatConfig.class.getClassLoader().getResourceAsStream("config.toml")).readAllBytes());
                    outputStream.flush();
                    outputStream.close();
                    InputStream inputStream = new FileInputStream(katConfigFile);
                    TomlParseResult toml = Toml.parse(inputStream);
                    for (Map.Entry<String, Object> entry : toml.dottedEntrySet()) {
                        configContent.put(entry.getKey(), entry.getValue());
                    }
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
