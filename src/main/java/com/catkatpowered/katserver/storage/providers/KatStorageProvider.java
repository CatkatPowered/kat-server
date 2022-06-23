package com.catkatpowered.katserver.storage.providers;

import com.catkatpowered.katserver.common.utils.KatShaUtils;
import com.catkatpowered.katserver.message.KatUniMessage;

import java.util.Optional;

/**
 * @author Krysztal
 */
public abstract class KatStorageProvider {

    protected KatStorageProvider() {
    }

    /**
     * 获取资源
     *
     * @param hashString 为想要获取文件的 Hash 值，约定使用 {@link KatShaUtils} 计算 Hash
     * @return 由 <b>Optional</b> 容器包装的包含资源文件的具体信息
     * @see KatShaUtils
     */
    public abstract Optional<KatUniMessage> fetch(String hashString);

    /**
     * 使用 Hash 校验资源，校验工具为 {@link KatShaUtils}
     *
     * @param resource 包含资源信息的数据结构
     * @return 由 <b>Optional</b> 容器包装的包含资源文件的具体信息
     * @see KatShaUtils
     */
    public abstract Optional<KatUniMessage> validate(String hashString);

    /**
     * 上传资源文件到指定位置。
     * 
     * @param resource 包含资源信息的数据结构
     * @return 由 <b>Optional</b> 容器包装的包含资源文件的具体信息
     */
    public abstract Optional<KatUniMessage> upload(KatUniMessage resource);

    /**
     * 删除资源
     *
     * @param resource 包含资源信息的数据结构
     * @return 是否成功删除
     */
    public abstract boolean delete(String hashString);

    /**
     * 更新资源
     *
     * @param resource 包含资源信息的数据结构
     * @return 由 <b>Optional</b> 容器包装的包含资源文件的具体信息
     */
    public abstract Optional<KatUniMessage> update(KatUniMessage resource);
}
