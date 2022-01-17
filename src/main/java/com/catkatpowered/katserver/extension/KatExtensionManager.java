package com.catkatpowered.katserver.extension;

/**
 * 扩展管理器
 *
 * @author hanbings
 */
public class KatExtensionManager {
    public static void KatExtensionMain() {
         KatExtensionLoader loader = new KatExtensionLoader();
        loader.loadExtensions();
    }
}
