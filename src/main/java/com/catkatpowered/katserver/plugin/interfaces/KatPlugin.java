package com.catkatpowered.katserver.plugin.interfaces;

/**
 * 接口定义一个 Kat 插件 用于扩展功能
 * @author hanbings
 */
public interface KatPlugin {
    void onEnable();
    void onLoad();
    void onDisable();
}
