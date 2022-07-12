package com.catkatpowered.katserver.storage;

import java.util.HashMap;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.common.constants.KatConfigNodeConstants;
import com.catkatpowered.katserver.common.constants.KatStorageTypeConstants;
import com.catkatpowered.katserver.storage.providers.KatStorageProvider;
import com.catkatpowered.katserver.storage.providers.local.LocalProvider;

import jdk.jfr.Experimental;
import lombok.Getter;

@Experimental
public class KatStorage {

    private static final KatStorage Instance = new KatStorage();

    private final HashMap<String, KatStorageProvider> storageProviders = new HashMap<String, KatStorageProvider>() {
        {
            put(KatStorageTypeConstants.KAT_STORAGE_PROVIDER_LOCAL, new LocalProvider());
        }
    };

    @Getter
    private KatStorageProvider provider;

    private KatStorage() {
        this.provider = this.storageProviders
                .get(KatServer.KatConfigAPI
                        .<String>getConfig(KatConfigNodeConstants.KAT_CONFIG_RESOURCE_STORAGE_PROVIDER)
                        .get());
    }

    public static KatStorage getInstance() {
        return Instance;
    }

    public void addStorage(String name, KatStorageProvider storage) {
        this.storageProviders.put(name, storage);
    }

}
