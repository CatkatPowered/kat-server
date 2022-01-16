package com.catkatpowered.katserver.common;

import com.catkatpowered.katserver.util.KatWorkingDir;

/**
 * Kat Server 所用到的目录
 *
 * @author hanbings
 * @author suibing112233
 */
public class KatResources {

    // 日志文件储存路径
    public static final String KAT_LOGGER_PATH = KatWorkingDir.fixPath("/log");

    // 桥模块存储路径
    public static final String KAT_BRIDGE_ROOT = KatWorkingDir.fixPath("/bridges");
    // 桥模块配置文件存储路径
    public static final String KAT_BRIDGE_CONFIG_ROOT = KatWorkingDir.fixPath("/bridges/config");

    // 插件存储路径
    public static final String KAT_PLUGIN_ROOT = KatWorkingDir.fixPath("/plugins");
    // 插件配置文件存储路径
    public static final String KAT_PLUGIN_CONFIG_ROOT = KatWorkingDir.fixPath("/plugins/config");
}
