package com.catkatpowered.katserver.storage.resource;

import java.util.Optional;

import com.catkatpowered.katserver.message.KatUniMessage;
import com.catkatpowered.katserver.storage.KatResource;

import org.jetbrains.annotations.NotNull;

public class ResourceLocalStorage implements ResourceStorage {

    @Override
    public Optional<KatResource> getResource(@NotNull KatUniMessage msg, ResourceAction action) {

        switch (action) {
            case Download -> {
                return this.download();
            }
            case ByHash -> {
                return this.byHash();
            }
            default -> {
                return Optional.empty();
            }
        }

    }

    private Optional<KatResource> byHash() {
        return Optional.empty();
    }

    private Optional<KatResource> download() {
        return null;
    }

}
