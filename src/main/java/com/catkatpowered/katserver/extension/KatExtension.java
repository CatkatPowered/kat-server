package com.catkatpowered.katserver.extension;

/**
 * 定义一个 Kat Server 扩展
 *
 * @author hanbings
 */
public interface KatExtension {
    void onEnable();
    void onLoad();
    void onDisable();
}
