package com.catkatpowered.katserver.common.utils;

import com.catkatpowered.katserver.common.constants.KatMiscConstants;
import java.nio.file.Path;

/**
 * KatServer 的工作目录有两种情况
 *
 * <li>如果定义了 <em> KAT_ENV_WORKING_DIR </em> 环境变量，则 KatServer 的工作目录由环境变量确定。</li>
 * <li>如果未定义 <em> KAT_ENV_WORKING_DIR </em> 环境变量，则 KatServer 的工作目录是程序运行目录。</li>
 *
 * @author Krysztal
 * @author CatMoe
 * @author hanbings
 */
public class KatWorkSpace {

  public static String getWorkingDir() {
    var workingDir = System.getenv().get(KatMiscConstants.KAT_ENV_WORKING_DIR);
    return workingDir == null || workingDir.isEmpty()
      ? System.getProperty("user.dir")
      : workingDir;
  }

  public static String fixPath(String path) {
    return Path.of(path).isAbsolute()
      ? path
      : KatWorkSpace.getWorkingDir() + path;
  }
}
