package com.catkatpowered.katserver.config;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.common.constants.KatConfigNodeConstants;
import com.catkatpowered.katserver.common.utils.KatWorkSpace;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Krysztal
 */
@Slf4j
public class KatConfigManager {

  public static void init() {
    if (
      !Files.exists(
        Path.of(
          KatWorkSpace.fixPath(
            KatServer.KatConfigAPI
              .<String>getConfig(
                KatConfigNodeConstants.KAT_CONFIG_RESOURCE_DATA_FOLDER_PATH
              )
              .get()
          )
        )
      )
    ) {
      try {
        Files.createDirectory(
          Path.of(
            KatWorkSpace.fixPath(
              KatServer.KatConfigAPI
                .<String>getConfig(
                  KatConfigNodeConstants.KAT_CONFIG_RESOURCE_DATA_FOLDER_PATH
                )
                .get()
            )
          )
        );
      } catch (IOException e) {
        log.error(String.valueOf(e));
      }
    }
  }

  @SuppressWarnings("unchecked")
  public static <T> Optional<T> getConfig(String configNode) {
    var optionalConfigNode = Optional.ofNullable(configNode);

    if (optionalConfigNode.isEmpty()) {
      return Optional.empty();
    }

    Object res = KatConfig.getInstance().getConfigContent().get(configNode);

    return Optional.ofNullable((T) res);
  }

  public static Map<String, Object> getAllConfig() {
    return KatConfig.getInstance().getConfigContent();
  }
}
