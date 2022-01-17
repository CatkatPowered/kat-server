package com.catkatpowered.katserver.util;

import com.catkatpowered.katserver.common.KatMiscConstants;


public class KatWorkingDir {

    public static String getWorkingDir() {
        var workingDir = System.getenv().get(KatMiscConstants.KAT_ENV_WORKING_DIR);

        return workingDir == null || workingDir.isEmpty()
                ? System.getProperty("user.dir")
                : workingDir;
    }

    public static String fixPath(String path) {
        return KatWorkingDir.getWorkingDir() + path;
    }
}
