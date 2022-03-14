package com.catkatpowered.katserver.storage.resource;

import com.catkatpowered.katserver.message.KatUniMessage;

import java.io.File;

public interface ResourceStorage {

    File getResource(String hash);

    File getResource(KatUniMessage msg);

    File getResource(KatUniMessage msg, boolean download);

}
