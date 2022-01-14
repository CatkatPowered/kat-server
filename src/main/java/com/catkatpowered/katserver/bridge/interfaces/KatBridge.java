package com.catkatpowered.katserver.bridge.interfaces;

/**
 * 接口定义一个 Kat 连接桥 用于扩展功能
 * @author hanbings
 */
public interface KatBridge {
    void onEnable();
    void onLoad();
    void onDisable();
}
