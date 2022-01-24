package com.catkatpowered.katserver.config;

import java.util.Map;

public class KatConfigManager {

    public static Object getConfig(String configNode) {
        return KatConfig.getInstance().getConfigContent().get(configNode);
    }

    public static Map<String, Object> getAllConfig() {
        return KatConfig.getInstance().getConfigContent();
    }
}
