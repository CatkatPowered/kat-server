package com.catkatpowered.katserver.storage.resource;

import com.catkatpowered.katserver.config.KatConfig;
import com.catkatpowered.katserver.message.KatUniMessage;
import java.io.File;
import org.jetbrains.annotations.NotNull;

public class LocalResourceStorage implements ResourceStorage {

    @Override
    public File getResource(@NotNull String hash) {
        File resource = new File(KatConfig.getInstance().getKatDataFolderPath()
            + "/media/"
            + hash.substring(0, 3)
            + "/"
            + hash);
        return resource.exists() ? resource : null;
    }

    @Override
    public File getResource(@NotNull KatUniMessage msg) {

        if (!msg.isHashed()) {
            return null;
        }

        File resource = new File(KatConfig.getInstance().getKatDataFolderPath()
            + "/media/"
            + msg.getResourceHash().substring(0, 3)
            + "/"
            + msg.getResourceHash());
        return resource.exists() ? resource : null;
    }

    @Deprecated
    @Override
    public File getResource(KatUniMessage msg, boolean download) {
        return null;
    }
}
