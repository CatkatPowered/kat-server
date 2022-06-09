package com.catkatpowered.katserver.storage.providers;

import java.util.Optional;

public interface KatStorageProvider {
    /**
     * 
     * 获取资源
     * 
     * @param resource
     * @return 包含资源文件的具体信息
     */
    Optional<KatResource> fetch(KatResource resource);

    /**
     * 
     * 校验资源
     * 
     * @param resource
     * @return 包含资源文件的具体信息
     */
    Optional<KatResource> validate(KatResource resource);

    /**
     * 
     * 上传资源
     * 
     * @param resource
     * @return 包含资源文件的具体信息
     */
    Optional<KatResource> upload(KatResource resource);

    /**
     * 
     * 删除资源
     * 
     * @param resource
     * @return 包含资源文件的具体信息
     */
    Optional<KatResource> delete(KatResource resource);

    /**
     * 
     * 更新资源
     * 
     * @param resource
     * @return 包含资源文件的具体信息
     */
    Optional<KatResource> update(KatResource resource);
}
