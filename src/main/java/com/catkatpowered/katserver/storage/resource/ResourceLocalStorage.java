package com.catkatpowered.katserver.storage.resource;

import com.catkatpowered.katserver.message.KatUniMessage;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

// TODO: 完成文件储存部分!!
@Deprecated
public class ResourceLocalStorage implements ResourceStorage {

    @Override
    public Optional<KatResource> getResource(@NotNull KatUniMessage msg, ResourceAction action) {

        switch (action) {
            case Download -> {
                return this.download();
            }
            case SearchByHash -> {
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
