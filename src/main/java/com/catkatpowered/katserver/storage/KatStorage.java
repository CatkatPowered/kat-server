package com.catkatpowered.katserver.storage;

import com.catkatpowered.katserver.common.constants.KatStorageTypeConstants;
import com.catkatpowered.katserver.storage.resource.ResourceLocalStorage;
import com.catkatpowered.katserver.storage.resource.ResourceStorage;
import jdk.jfr.Experimental;

import java.util.HashMap;

// TODO：编写选择储存方案的逻辑
@Experimental
public class KatStorage {

    private static final KatStorage Instance = new KatStorage();

    private KatStorage() {
    }

    public static KatStorage getInstance() {
        return Instance;
    }

    public void addStorage(String resourceName, ResourceStorage storage) {
        this.storageTarget.put(resourceName, storage);
    }    private final HashMap<String, ResourceStorage> storageTarget = new HashMap<String, ResourceStorage>() {
        {
            storageTarget.put(KatStorageTypeConstants.KAT_STORAGE_TYPE_LOCAL, new ResourceLocalStorage());
        }
    };




}
