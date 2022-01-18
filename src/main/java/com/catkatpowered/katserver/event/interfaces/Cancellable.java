package com.catkatpowered.katserver.event.interfaces;

/**
 * 一个 Event 接口, 实现该接口标识事件可以取消 <br>
 * 请注意这一个设计, 取消并不意味这事件会在 setCanceled(true) 后停止向下一个事件处理器传播 <br>
 * 如果事件处理器的注解中 ignoreCanceled 值为 false, 事件处理器将会被正常触发 <br>
 * 反之则不会被触发, 完全阻断事件请使用 com.catkatpowered.katserver.event.interfaces.Blockable 接口
 */
@SuppressWarnings("unused SpellCheckingInspection")
public interface Cancellable {
    void setCancelled(boolean cancel);
    boolean isCancelled();
}
