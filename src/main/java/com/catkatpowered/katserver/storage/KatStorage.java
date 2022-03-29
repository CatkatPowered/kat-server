package com.catkatpowered.katserver.storage;

import java.util.HashMap;

import com.catkatpowered.katserver.common.constants.KatStorageTypeConstants;
import com.catkatpowered.katserver.storage.resource.ResourceLocalStorage;
import com.catkatpowered.katserver.storage.resource.ResourceStorage;

import jdk.jfr.Experimental;

// TODO 编写选择储存方案的逻辑
@Experimental
public class KatStorage {

    private static final KatStorage Instance = new KatStorage();
    private final HashMap<String, ResourceStorage> storageTarget = new HashMap<String, ResourceStorage>() {
        {
            storageTarget.put(KatStorageTypeConstants.KAT_STORAGE_TYPE_LOCAL, new ResourceLocalStorage());
        }
    };

    private KatStorage() {
    }

    public void addStorage(String resourceName, ResourceStorage storage) {
        this.storageTarget.put(resourceName, storage);
    }

    public static KatStorage getInstance() {
        return Instance;
    }
}
