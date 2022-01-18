package com.catkatpowered.katserver.extension;

import java.io.File;

/**
 * 扩展管理器
 *
 * @author hanbings
 */
public class KatExtensionManager {
    static KatExtensionLoader loader = new KatExtensionLoader();

    public static void KatExtensionMain() {
        KatExtensionManager.loadExtensions();
    }

    /**
     * 加载所有符合要求的扩展
     */
    public static void loadExtensions() {
        loader.loadExtensions();
    }

    /**
     * 加载一个扩展
     *
     * @param jar 扩展文件
     */
    public static void loadExtension(File jar) {
        loader.loadExtension(jar);
    }

    /**
     * 卸载全部扩展
     */
    public static void unloadExtensions() {
        loader.unloadExtensions();
    }

    /**
     * 卸载一个扩展
     *
     * @param extension 扩展名
     */
    public static void unloadExtension(String extension) {
        loader.unloadExtension(extension);
    }

    /**
     * 卸载一个扩展
     *
     * @param extension 扩展实例
     */
    public static void unloadExtension(KatExtension extension) {
        loader.unloadExtension(extension);
    }
}
