package com.catkatpowered.katserver.storage.providers;

import java.util.Optional;

public abstract class KatStorageProvider {

    protected KatStorageProvider() {
    }

    /**
     * 
     * 获取资源
     * 
     * @param resource 包含资源信息的数据结构
     * @return 由 <b>Optional</b> 容器包装的包含资源文件的具体信息
     */
    public abstract Optional<KatResource> fetch(KatResource resource);

    /**
     * 
     * 校验资源
     * 
     * @param resource 包含资源信息的数据结构
     * @return 由 <b>Optional</b> 容器包装的包含资源文件的具体信息
     */
    public abstract Optional<KatResource> validate(KatResource resource);

    /**
     * 
     * 上传资源
     * 
     * @param resource 包含资源信息的数据结构
     * @return 由 <b>Optional</b> 容器包装的包含资源文件的具体信息
     */
    public abstract Optional<KatResource> upload(KatResource resource);

    /**
     * 
     * 删除资源
     * 
     * @param resource 包含资源信息的数据结构
     * @return 由 <b>Optional</b> 容器包装的包含资源文件的具体信息
     */
    public abstract Optional<KatResource> delete(KatResource resource);

    /**
     * 
     * 更新资源
     * 
     * @param resource 包含资源信息的数据结构
     * @return 由 <b>Optional</b> 容器包装的包含资源文件的具体信息
     */
    public abstract Optional<KatResource> update(KatResource resource);
}
