package com.catkatpowered.katserver.bridge;

import com.catkatpowered.katserver.log.KatLogger;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * 类定义了一个基本的 Kat 桥
 * @author hanbings
 * @author suibing112233
 */
@SuppressWarnings("unused")
public abstract class KatBridge {
    // 桥版本
    @Setter @Getter
    private String version = "1.0.0";
    // 桥标识名称
    @Setter @Getter
    private String identify = "default";
    // 桥模块作者
    @Setter @Getter
    private String[] author = {"default", "default"};
    // 桥模块网站
    @Setter @Getter
    private String website = "https://github.com";

    // 抽象方法 在桥被加载时会执行此代码
    public abstract void onEnable();
    // 抽象方法 在桥被卸载时会执行此代码
    public abstract void onDisable();

    // 已经处理成协议的消息 推送至核心
    public void pushMessage(String message) {}
    // 抽象方法 当核心下派了消息后传递给桥 由桥转发至 im
    public abstract void sendMessage(String message);

    // 图片 视频 语音 文件 推送至核心
    public void pushPicture(File file, String messageCode) {}
    public void pushVideo(File file, String messageCode) {}
    public void pushVoice(File file, String messageCode) {}
    public void pushFile(File file, String messageCode) {}
    // 从核心获取 图片 视频 语音 文件
    public File getPicture(String sha256) {return null;}
    public File getVideo(String sha256) {return null;}
    public File getVoice(String sha256) {return null;}
    public File getFile(String sha256) {return null;}
}
