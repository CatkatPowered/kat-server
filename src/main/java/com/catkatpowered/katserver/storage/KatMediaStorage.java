package com.catkatpowered.katserver.storage;

import com.catkatpowered.katserver.KatConfig;

import java.io.File;
import java.io.InputStream;

public class KatMediaStorage {
    public static Boolean KatMediaPut(InputStream inputStream){

        return true;
    }

    public static File KatMediaGet(String mediaHash) {
        String mediaPath = KatConfig.KAT_DATA_FOLDER_PATH + "/media/" + mediaHash.substring(0,3) + "/" + mediaHash;
        File media = new File(mediaPath);
        if (media.exists()) {
            return media;
        } else {
            return null;
        }
    }
}
