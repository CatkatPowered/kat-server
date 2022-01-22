package com.catkatpowered.katserver.extension;

import java.io.File;

/**
 * 扩展管理器
 *
 * @author hanbings
 */
public class KatExtensionManager {

    public static void init() {
        KatExtensionManager.loadExtensions();
    }

    /**
     * 加载所有符合要求的扩展
     */
    public static void loadExtensions() {
        KatExtensionLoader.getInstance().loadExtensions();
    }

    /**
     * 加载一个扩展
     *
     * @param jar 扩展文件
     */
    public static void loadExtension(File jar) {
        KatExtensionLoader.getInstance().loadExtension(jar);
    }

    /**
     * 卸载全部扩展
     */
    public static void unloadExtensions() {
        KatExtensionLoader.getInstance().unloadExtensions();
    }

    /**
     * 卸载一个扩展
     *
     * @param extension 扩展名
     */
    public static void unloadExtension(String extension) {
        KatExtensionLoader.getInstance().unloadExtension(extension);
    }

    /**
     * 卸载一个扩展
     *
     * @param extension 扩展实例
     */
    public static void unloadExtension(KatExtension extension) {
        KatExtensionLoader.getInstance().unloadExtension(extension);
    }
}
