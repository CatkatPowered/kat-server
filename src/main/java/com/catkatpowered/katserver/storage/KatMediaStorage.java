package com.catkatpowered.katserver.storage;

import com.catkatpowered.katserver.config.KatConfig;

import java.io.File;
import java.io.InputStream;

public class KatMediaStorage {

    public static Boolean putMedia(InputStream inputStream) {

        return true;
    }

    public static File getMedia(String mediaHash) {
        File media = new File(KatConfig.getInstance().getKatDataFolderPath()
                + "/media/"
                + mediaHash.substring(0, 3)
                + "/"
                + mediaHash);
        return media.exists() ? media : null;
    }
}
