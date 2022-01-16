package com.catkatpowered.katserver.storage;

import com.catkatpowered.katserver.KatConfig;

import java.io.File;
import java.io.InputStream;

public class KatMediaStorage {

  public static Boolean putMedia(InputStream inputStream) {

    return true;
  }

  public static File getMedia(String mediaHash) {
    String mediaPath = String.valueOf(
        new StringBuilder()
            .append(KatConfig.getKatDataFolderPath())
            .append("/media/")
            .append(mediaHash.substring(0, 3))
            .append("/")
            .append(mediaHash));

    File media = new File(mediaPath);
    return media.exists() ? media : null;
  }
}
