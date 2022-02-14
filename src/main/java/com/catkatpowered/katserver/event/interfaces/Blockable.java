package com.catkatpowered.katserver.event.interfaces;

/**
 * 一个 Event 接口, 实现该接口标识事件可以阻断 <br>
 * 使用 setBlocked(true) 来完全阻断事件, 即使事件处理器的注解中 ignoreCancelled 值为 false <br>
 * 事实上, 在 setBlocked(true) 后其他优先级比当前事件处理器低的处理器将被忽略, 直接结束当前一次 callEvent <br>
 * 如果不是必要的, 仍推荐使用 com.catkatpowered.katserver.event.interfaces.Cancellable 接口
 */
@SuppressWarnings("unused SpellCheckingInspection")
public interface Blockable {
    void setBlocked(boolean block);
    boolean isBlocked();
}
