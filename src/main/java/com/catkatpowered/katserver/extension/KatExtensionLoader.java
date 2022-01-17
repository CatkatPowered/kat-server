package com.catkatpowered.katserver.extension;

import com.catkatpowered.katserver.common.KatResources;
import com.catkatpowered.katserver.log.KatLogger;
import com.google.gson.Gson;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
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
    // 扩展映射图与扩展实例图以及一个描述文件索引
    static Map<String, KatExtensionInfo> index = new HashMap<>();
    static Map<KatExtensionInfo, Class<?>> mapping = new HashMap<>();
    static Map<KatExtensionInfo, Object> extensions = new HashMap<>();

    /**
     * 加载扩展流程 <br>
     * 扫描扩展目录 找到文件名符合的扩展 <br>
     * -> 读取扩展的描述文件 <br>
     * -> TODO: 根据描述文件中所定义的依赖顺序排列扩展加载顺序 <br>
     * -> 通过 ClassLoader 加载描述文件中所定义的主类 <br>
     * -> 在 onLoad 以前注入所需的模块 <br>
     * -> 分别执行 onLoad onEnable onDisable <br>
     * -> 释放无用资源 <br>
     * 加载完成 <br>
     * <p>
     * 此方法为入口方法
     */
    public void loadExtensions() {
        // 获取扩展文件
        List<File> jars = this.getExtensions();
        for (File jar : jars) {
            // 扩展描述文件
            KatExtensionInfo info = this.getExtensionInfo(jar);
            // 建立描述文件索引
            index.put(info.extension, info);
            // 获取扩展主类
            Class<?> clazz = loadJar(jar, info.main);
            // 保留映射
            mapping.put(info, clazz);
            // 加载
            this.loadExtension(clazz, info);
        }
    }

    /**
     * 注入扩展依赖并调用单个扩展方法
     *
     * @param clazz 扩展主类
     * @param info  扩展描述
     */
    public void loadExtension(Class<?> clazz, KatExtensionInfo info) {
        try {
            // 新对象
            Object extension = clazz.getDeclaredConstructor().newInstance();
            extensions.put(info, extension);
            // 获取方法 -> 注入日志 -> 按顺序调用
            clazz.getMethod("setLogger", Logger.class).invoke(extension, KatLogger.getLogger(info.extension));
            clazz.getMethod("onLoad").invoke(extension);
            clazz.getMethod("onEnable").invoke(extension);
        } catch (InstantiationException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException exception) {
            logger.error("unknown error. cloud not load {} extension.", info.extension, exception);
        }
    }

    /**
     * 卸载一个扩展
     *
     * @param extension 扩展名
     */
    public void unloadExtension(String extension) {
        KatExtensionInfo info = index.get(extension);
        try {
            mapping.get(info).getMethod("onDisable").invoke(extensions.get(info));
            // 卸载成功后移除索引与图
            extensions.remove(info);
            mapping.remove(info);
            index.remove(extension);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException exception) {
            logger.error("unload {} extension error.", info.extension, exception);
        }
    }

    /**
     * 卸载一个扩展
     *
     * @param extension 扩展实例
     */
    public void unloadExtension(KatExtension extension) {
        // extension 表获取描述文件实体类
        KatExtensionInfo info = (KatExtensionInfo) extensions.entrySet()
                .stream()
                .filter(kvEntry -> Objects.equals(kvEntry.getValue(), extension))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        try {
            mapping.get(info).getMethod("onDisable").invoke(extensions.get(info));
            // 卸载成功后移除索引与图
            extensions.remove(info);
            mapping.remove(info);
            index.remove(info.extension);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException exception) {
            logger.error("unload {} extension error.", info.extension, exception);
        }
    }

    /**
     * 正式加载扩类文件
     *
     * @param extension 扩展文件
     * @param main      主类
     * @return 扩展类
     */
    public Class<?> loadJar(File extension, String main) {
        try {
            URLClassLoader loader = new URLClassLoader(new URL[]{extension.toURI().toURL()});
            return loader.loadClass(main);
        } catch (MalformedURLException | ClassNotFoundException exception) {
            logger.error("cloud not load {} extension.", extension.getName(), exception);
        }
        return null;
    }

    /**
     * 获取描述文件实体类
     *
     * @param extension 扩展文件对象
     * @return 描述文件实体类或者失败时返回 null
     */
    public KatExtensionInfo getExtensionInfo(File extension) {
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
