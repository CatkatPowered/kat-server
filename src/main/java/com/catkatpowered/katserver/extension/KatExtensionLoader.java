package com.catkatpowered.katserver.extension;

import com.catkatpowered.katserver.common.KatResources;
import com.catkatpowered.katserver.log.KatLogger;
import com.google.gson.Gson;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

/**
 * 读取 扩展 排序 并按依赖排序加载
 *
 * @author hanbings
 */
public class KatExtensionLoader {
    static Logger logger = KatLogger.getLogger("Kat Extension Loader");
    static Gson gson = new Gson();

    /**
     * 获取配置文件实体类
     *
     * @param extension 扩展文件对象
     * @return 配置文件实体类或者失败时返回 null
     */
    public KatExtensionConfig getExtensionConfig(File extension) {
        try {
            JarFile jar = new JarFile(extension);
            // 获得 Input Stream
            InputStream stream = jar.getInputStream(jar.getJarEntry("extension.json"));
            // 转 String
            String json = new BufferedReader(new InputStreamReader(stream))
                    .lines().collect(Collectors.joining(System.lineSeparator()));
            // 注入依赖
            return gson.fromJson(json, KatExtensionConfig.class);
        } catch (IOException exception) {
            logger.error("Are you ready {} is a extension?", extension.getName(), exception);
        }
        return null;
    }

    /**
     * 扫描扩展目录 寻找可加载扩展 <br>
     * 这里仅检查文件后缀名
     *
     * @return 包含符合要求可能的扩展文件
     */
    public List<File> getExtensions() {
        List<File> list = new ArrayList<>();
        File path = new File(KatResources.KAT_EXTENSIONS_ROOT);
        File[] files = path.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".jar")) {
                    list.add(file);
                }
            }
        }
        return list;
    }
}
