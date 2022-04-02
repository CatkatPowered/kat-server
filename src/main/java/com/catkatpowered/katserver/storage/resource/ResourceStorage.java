package com.catkatpowered.katserver.storage.resource;

import com.catkatpowered.katserver.message.KatUniMessage;
import com.catkatpowered.katserver.storage.KatResource;

import java.util.Optional;

public interface ResourceStorage {

    Optional<KatResource> getResource(KatUniMessage msg, ResourceAction action);

}
