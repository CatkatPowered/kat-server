package com.catkatpowered.katserver.storage;

import java.util.Optional;

import com.catkatpowered.katserver.storage.providers.KatResource;
import com.catkatpowered.katserver.storage.providers.KatStorageProvider;

public class KatStorageManager {

    public static void init() {
        KatStorage.getInstance();
    }

    /**
     * 获取储存提供者
     * 
     * @return 配置文件中所配置的 KatStorageProvider
     * @see KatStorageProvider
     */
    public static KatStorageProvider getStorageProvider() {
        return KatStorage.getInstance().getProvider();
    }

    /**
     * 拉取资源位置。
     * 
     * 注意，该函数返回值中为URI，需要做适当的处理
     * 
     * @param hashString
     * @return
     */
    public static Optional<KatResource> fetch(String hashString) {
        return KatStorage.getInstance().getProvider().fetch(hashString);
    }

    /**
     * 校验文件是否正确
     * 
     * @param hashString
     * @return
     */
    public static Optional<KatResource> validate(String hashString) {
        return KatStorage.getInstance().getProvider().validate(hashString);
    }

    /**
     * 上传文件
     * 
     * @param resource 包含信息的容器
     * @return
     */
    public static Optional<KatResource> upload(KatResource resource) {
        return KatStorage.getInstance().getProvider().upload(resource);
    }

    /**
     * 删除文件。
     * 
     * @param hashString 文件hash值
     * @return
     */
    public static boolean delete(String hashString) {
        return KatStorage.getInstance().getProvider().delete(hashString);
    }

    /**
     * 更新文件。
     * 
     * @param resource
     * @return
     */
    public static Optional<KatResource> update(KatResource resource) {
        return KatStorage.getInstance().getProvider().update(resource);
    }

}
