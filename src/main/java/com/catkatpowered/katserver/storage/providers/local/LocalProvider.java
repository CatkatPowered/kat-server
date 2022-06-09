package com.catkatpowered.katserver.storage.providers.local;

import java.util.Optional;

import com.catkatpowered.katserver.storage.providers.KatResource;
import com.catkatpowered.katserver.storage.providers.KatStorageProvider;

public class LocalProvider implements KatStorageProvider {

    @Override
    public Optional<KatResource> fetch(KatResource resource) {
        return Optional.empty();
    }

    @Override
    public Optional<KatResource> validate(KatResource resource) {
        return Optional.empty();
    }

    @Override
    public Optional<KatResource> upload(KatResource resource) {
        return Optional.empty();
    }

    @Override
    public Optional<KatResource> delete(KatResource resource) {
        return Optional.empty();
    }

    @Override
    public Optional<KatResource> update(KatResource resource) {
        return Optional.empty();
    }

}
