package com.catkatpowered.katserver.storage.providers;

import com.catkatpowered.katserver.common.utils.KatShaUtils;
import java.io.InputStream;
import org.jetbrains.annotations.Nullable;

/**
 * @author Krysztal
 */
public interface KatStorageProvider {

  /**
   * 获取资源
   *
   * @param hashString 为想要获取文件的 Hash 值，约定使用 {@link KatShaUtils}
   *     计算 Hash
   * @return 由 <b>Optional</b> 容器包装的包含资源文件的具体信息
   * @see KatShaUtils
   */
  @Nullable public InputStream fetch(String hashString);

  /**
   * 使用 Hash 校验资源，校验工具为 {@link KatShaUtils}
   *
   * @param resource 包含资源信息的数据结构
   * @return 由 <b>Optional</b> 容器包装的包含资源文件的具体信息
   * @see KatShaUtils
   */
  public boolean validate(String hashString);

  /**
   * 上传资源文件到指定位置。
   *
   * @param resource 包含资源信息的数据结构
   * @return 由 <b>Optional</b> 容器包装的包含资源文件的具体信息
   */
  public void upload(String fileHash, InputStream inputStream);

  /**
   * 删除资源
   *
   * @param resource 包含资源信息的数据结构
   * @return 是否成功删除
   */
  public boolean delete(String hashString);
}
