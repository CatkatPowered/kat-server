package com.catkatpowered.katserver.common.utils;

import com.catkatpowered.katserver.common.constants.KatMiscConstants;

import java.nio.file.Path;

/**
 * Kat Server的工作目录
 *
 * @author suibing112233
 * @author CatMoe
 */
public class KatWorkingDir {

    public static String getWorkingDir() {
        var workingDir = System.getenv().get(KatMiscConstants.KAT_ENV_WORKING_DIR);

        return workingDir == null || workingDir.isEmpty()
                ? System.getProperty("user.dir")
                : workingDir;
    }

    public static String fixPath(String path) {
        return Path.of(path).isAbsolute()
                ? path
                : KatWorkingDir.getWorkingDir() + path;
    }
}
