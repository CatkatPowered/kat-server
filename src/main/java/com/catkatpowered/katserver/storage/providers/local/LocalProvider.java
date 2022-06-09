package com.catkatpowered.katserver.storage.providers.local;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.common.constants.KatConfigNodeConstants;
import com.catkatpowered.katserver.storage.providers.KatResource;
import com.catkatpowered.katserver.storage.providers.KatStorageProvider;

public class LocalProvider extends KatStorageProvider {

    private final String resourceStoragePath;

    public LocalProvider() {
        super();
        resourceStoragePath = KatServer.KatConfigAPI
                .<String>getConfig(KatConfigNodeConstants.KAT_CONFIG_RESOURCE_DATA_FOLDER_PATH)
                .get();
    }

    /**
     * 获取资源，其中 URI字段为
     * 
     * 
     */
    @Override
    public Optional<KatResource> fetch(KatResource resource) {
        var path = hashToPath(resource);
        var file = new File(path.get());

        if (path.isEmpty() || (!(new File(path.get())).exists()))
            return Optional.empty();

        return Optional.of(KatResource
                .builder()
                .hash(resource.hash)
                .size(file.length())
                .uri(file.toURI())
                .build());
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

    private Optional<String> hashToPath(KatResource resource) {
        return Optional.of(Path.of(
                this.resourceStoragePath,
                resource.hash.substring(0, 2))
                .toAbsolutePath()
                .toString());
    }

}
