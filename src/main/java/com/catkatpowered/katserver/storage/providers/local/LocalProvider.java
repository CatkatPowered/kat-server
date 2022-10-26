package com.catkatpowered.katserver.storage.providers.local;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.common.constants.KatConfigNodeConstants;
import com.catkatpowered.katserver.common.utils.KatDownload;
import com.catkatpowered.katserver.common.utils.KatShaUtils;
import com.catkatpowered.katserver.common.utils.KatWorkSpace;
import com.catkatpowered.katserver.storage.providers.KatStorageProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

import org.jetbrains.annotations.Nullable;

/**
 * @author Krysztal
 */
public class LocalProvider implements KatStorageProvider {

  private final String resourceStoragePath;

  public LocalProvider() {
    super();
    resourceStoragePath =
        KatServer.KatConfigAPI
            .<String>getConfig(
                KatConfigNodeConstants.KAT_CONFIG_RESOURCE_DATA_FOLDER_PATH)
            .orElse(KatWorkSpace.fixPath("./data"));
  }

  /**
   * 通过文件 Hash 获取资源文件
   *
   * @return URI 字段为文件于磁盘中的位置
   * @see java.net.URI
   * @see Optional
   * @see com.catkatpowered.katserver.common.utils.KatShaUtils
   */
  @Override
  @Nullable
  public InputStream fetch(String hashString) {
    var path = toPath(hashString);
    if (path == null)
      return null;

    var file = new File(path);
    if (!(new File(path)).exists())
      return null;

    InputStream fileInputStream = null;

    try {
      fileInputStream = new FileInputStream(file);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return fileInputStream;
  }

  @Override
  public boolean validate(String hashString) {
    // 判断是否存在文件，若不存在则返回空 Optional
    var fetchedResource = this.fetch(hashString);
    if (fetchedResource == null)
      return false;

    // 由于是本地文件，因此直接计算文件Sha256
    var fileSha256 = KatShaUtils.sha256(fetchedResource);

    try {
      fetchedResource.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return Objects.equals(hashString, fileSha256);
  }

  @Override
  public void upload(String fileHash, InputStream inputStream) {

    var filePath = this.toPath(fileHash);

    KatDownload.writeFile(new File(filePath), inputStream);
  }

  @Override
  public boolean delete(String hashString) {
    var fetchedFilePath = this.fetch(hashString);
    if (fetchedFilePath == null)
      return false;

    var file = new File(this.toPath(hashString));
    return file.delete();
  }

  private String toPath(String hashString) {
    return Path
        .of(this.resourceStoragePath, hashString.substring(0, 2),
            hashString.substring(2, 4))
        .toAbsolutePath()
        .toString();
  }
}
