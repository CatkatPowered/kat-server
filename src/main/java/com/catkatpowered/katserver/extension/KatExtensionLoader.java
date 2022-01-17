package com.catkatpowered.katserver.extension;

import com.catkatpowered.katserver.common.KatResources;
import com.catkatpowered.katserver.log.KatLogger;
import com.google.gson.Gson;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
     * 加载扩展流程 <br>
     * 扫描扩展目录 找到文件名符合的扩展 <br>
     * -> 读取扩展的配置文件 <br>
     * -> TODO: 根据配置文件中所定义的依赖顺序排列扩展加载顺序 <br>
     * -> 通过 ClassLoader 加载配置文件中所定义的主类 <br>
     * -> 在 onLoad 以前注入所需的模块 <br>
     * -> 分别执行 onLoad onEnable onDisable <br>
     * -> 释放无用资源 <br>
     * 加载完成
     *
     * 此方法为入口方法
     */
    public void loadExtension() {
        List<File> jars = this.getExtensions();
        // 扩展文件与扩展配置文件成对存放
        Map<File, KatExtensionInfo> mapping = new HashMap<>();
        for(File jar : jars) {
            mapping.put(jar, this.getExtensionConfig(jar));
        }
        // 遍历加载
    }

    /**
     * 获取配置文件实体类
     *
     * @param extension 扩展文件对象
     * @return 配置文件实体类或者失败时返回 null
     */
    public KatExtensionInfo getExtensionConfig(File extension) {
        try {
            JarFile jar = new JarFile(extension);
            // 获得 Input Stream
            InputStream stream = jar.getInputStream(jar.getJarEntry("extension.json"));
            // 转 String
            String json = new BufferedReader(new InputStreamReader(stream))
                    .lines().collect(Collectors.joining(System.lineSeparator()));
            // 注入依赖
            return gson.fromJson(json, KatExtensionInfo.class);
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
