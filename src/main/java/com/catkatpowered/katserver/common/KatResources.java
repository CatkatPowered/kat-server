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

    // 扩展存储路径
    public static final String KAT_EXTENSIONS_ROOT = KatWorkingDir.fixPath("/extension");
    // 扩展配置文件存储路径
    public static final String KAT_EXTENSIONS_CONFIG_ROOT = KatWorkingDir.fixPath("/extension/config");

    // SQLite 数据库存储路径
    public static final String SQLITE_DATABASE_PATH = KatWorkingDir.fixPath("./data/database.db");
}
