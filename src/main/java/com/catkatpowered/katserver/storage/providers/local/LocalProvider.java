package com.catkatpowered.katserver.storage.providers.local;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.common.constants.KatConfigNodeConstants;
import com.catkatpowered.katserver.common.utils.KatShaUtils;
import com.catkatpowered.katserver.storage.providers.KatResource;
import com.catkatpowered.katserver.storage.providers.KatStorageProvider;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Krysztal
 */
public class LocalProvider extends KatStorageProvider {

    private final String resourceStoragePath;

    public LocalProvider() {
        super();
        resourceStoragePath = KatServer.KatConfigAPI.<String>getConfig(KatConfigNodeConstants.KAT_CONFIG_RESOURCE_DATA_FOLDER_PATH).orElse("./data");
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
    public Optional<KatResource> fetch(String hashString) {
        var path = toPath(hashString);
        if (path.isEmpty()) return Optional.empty();

        var file = new File(path.get());
        if (!(new File(path.get())).exists()) return Optional.empty();

        return Optional.of(KatResource.builder().hash(hashString).size(file.length()).uri(file.toURI()).build());
    }

    @Override
    public Optional<KatResource> validate(String hashString) {
        // 判断是否存在文件，若不存在则返回空 Optional
        var fetchedKatResource = this.fetch(hashString);
        if (fetchedKatResource.isEmpty()) return Optional.empty();

        // 由于是本地文件，因此直接计算文件Sha256
        var fileURI = fetchedKatResource.get().getUri();
        var fileSha256 = KatShaUtils.sha256(new File(fileURI));

        return !Objects.equals(hashString, fileSha256) ? Optional.empty() : fetchedKatResource;
    }

    // TODO: 等待DownloadUtil
    @Override
    public Optional<KatResource> upload(KatResource resource) {
        return Optional.empty();
    }

    @Override
    public boolean delete(String hashString) {
        var fetchedOptionalKatResource = this.fetch(hashString);
        if (fetchedOptionalKatResource.isEmpty()) return false;

        var file = new File(fetchedOptionalKatResource.get().getUri());
        return file.delete();
    }

    @Override
    public Optional<KatResource> update(KatResource resource) {
        return Optional.empty();
    }

    private Optional<String> toPath(String hashString) {
        return Optional.of(Path.of(this.resourceStoragePath, hashString.substring(0, 2)).toAbsolutePath().toString());
    }

}
