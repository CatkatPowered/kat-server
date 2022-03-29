package com.catkatpowered.katserver.storage.resource;

import java.util.Optional;

import com.catkatpowered.katserver.message.KatUniMessage;
import com.catkatpowered.katserver.storage.KatResource;

public interface ResourceStorage {

    Optional<KatResource> getResource(KatUniMessage msg, ResourceAction action);

}
