package com.catkatpowered.katserver.common.constants;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.common.utils.KatWorkSpace;

/**
 * 提供常量
 *
 * @author hanbings
 * @author Krysztal
 * @author CatMoe
 */
public class KatMiscConstants {

    // Kat Server 项目名
    public static final String KAT_PROJECT_NAME = "KatServer";

    // Kat Server 版本号
    public static final String KAT_SERVER_VERSION = "1.0.0";

    // Kat Server 字符画
    public static final String KAT_SERVER_LOGO = """

             _  __     _    _____                         \\s
            | |/ /    | |  / ____|                        \\s
            | ' / __ _| |_| (___   ___ _ ____   _____ _ __\\s
            |  < / _` | __|\\\\___ \\\\ / _ \\\\ '__\\\\ \\\\ / / _ \\\\ '__|
            | . \\\\ (_| | |_ ____) |  __/ |   \\\\ V /  __/ |  \\s
            |_|\\\\_\\\\__,_|\\\\__|_____/ \\\\___|_|    \\\\_/ \\\\___|_|  \\s
                                                          \\s
            """;

    // Kat Server 环境变量：KAT_ENV_WORKING_DIR
    public static final String KAT_ENV_WORKING_DIR = "KAT_ENV_WORKING_DIR";

    // 日志文件储存路径
    public static final String KAT_LOGGER_PATH = KatWorkSpace.fixPath("/log");

    // 扩展存储路径
    public static final String KAT_EXTENSIONS_ROOT = KatWorkSpace.fixPath("/extension");
    // 扩展配置文件存储路径
    public static final String KAT_EXTENSIONS_CONFIG_ROOT = KatWorkSpace.fixPath("/extension/config");

    // SQLite 数据库存储路径
    public static final String KAT_DATABASE_PATH = KatWorkSpace.fixPath(KatServer.KatConfigAPI.getConfig(KatConfigNodeConstants.KAT_CONFIG_RESOURCE_DATA_FOLDER_PATH) + "/database.db");

}
