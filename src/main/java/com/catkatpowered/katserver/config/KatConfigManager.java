package com.catkatpowered.katserver.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.common.constants.KatConfigNodeConstants;
import com.catkatpowered.katserver.common.utils.KatWorkingDir;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KatConfigManager {

    public static void init() {
        if (!Files.exists(Path.of(KatWorkingDir.fixPath(
                KatServer.KatConfigAPI.getConfig(KatConfigNodeConstants.KAT_CONFIG_DATA_FOLDER_PATH).toString())))) {
            try {
                Files.createDirectory(Path.of(KatWorkingDir.fixPath(
                        KatServer.KatConfigAPI.getConfig(KatConfigNodeConstants.KAT_CONFIG_DATA_FOLDER_PATH)
                                .toString())));
            } catch (IOException e) {
                log.error(String.valueOf(e));
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Optional<T> getConfig(String configNode) {
        var nodes = configNode.split("\\.");
        Map<String, Object> res = KatConfig.getInstance().getConfigContent();
        for (int i = 0; i < nodes.length - 1; i++) {
            res = (Map<String, Object>) res.get(nodes[i]);
        }
        return Optional.ofNullable((T) res.get(nodes[nodes.length - 1]));
    }

    public static Map<String, Object> getAllConfig() {
        return KatConfig.getInstance().getConfigContent();
    }
}
