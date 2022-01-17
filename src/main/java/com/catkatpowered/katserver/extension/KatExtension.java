package com.catkatpowered.katserver.extension;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.Logger;

/**
 * 定义一个 Kat Server 扩展 <br>
 * 加载顺序 onLoad -> onEnable -> onDisable <br>
 * 在 onLoad 过程核心会读取扩展的依赖 检查扩展是否符合标准 检查扩展权限 <br>
 * 抽象类应向扩展提供已经实现的 API 方法 由扩展实现抽象方法提供给核心调用
 *
 * @author hanbings
 */
@SuppressWarnings("unused")
public abstract class KatExtension {
    // 配置文件对象 需要由加载器来注入
    @Setter @Getter
    private static KatExtensionConfig extensionConfig;

    // 日志 日志名从 config 中获取 同样由加载器注入
    @Setter @Getter
    private static Logger logger;

    /**
     * 在插件被时执行此方法 抽象方法 由扩展实现
     */
    public abstract void onLoad();

    /**
     * 在插件被开启时执行此方法 抽象方法 由扩展实现
     */
    public abstract void onEnable();

    /**
     * 在插件被卸载时执行此方法 抽象方法 由扩展实现
     */
    public abstract void onDisable();
}
