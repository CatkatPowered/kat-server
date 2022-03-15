package com.catkatpowered.katserver.extension;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.event.Event;
import com.catkatpowered.katserver.extension.event.DisableExtensionEvent;
import com.catkatpowered.katserver.extension.event.EnableExtensionEvent;
import com.catkatpowered.katserver.extension.event.ExtensionEvent;
import com.catkatpowered.katserver.extension.event.LoadExtensionEvent;
import org.checkerframework.checker.units.qual.K;

import java.io.File;

/**
 * 扩展管理器
 *
 * @author hanbings
 */
public class KatExtensionManager {

    public static void init() {
        // 注册 Event
        KatServer.KatEventBusAPI.registerEvent(new ExtensionEvent(new KatExtensionInfo()));
        KatServer.KatEventBusAPI.registerEvent(new LoadExtensionEvent(new KatExtensionInfo()));
        KatServer.KatEventBusAPI.registerEvent(new EnableExtensionEvent(new KatExtensionInfo()));
        KatServer.KatEventBusAPI.registerEvent(new DisableExtensionEvent(new KatExtensionInfo()));

        // 加载所有扩展
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
