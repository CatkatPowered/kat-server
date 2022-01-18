package com.catkatpowered.katserver.event;

/**
 * 很高兴的告诉您, 我在抄bukkit的同时 <br>
 * 把这个反人类设计一起抄过来了 <br>
 * 这个枚举使用在 @EventHandler 注解的 priority 参数中 <br>
 * 序号越大的优先级越低, 请不要被字面意思迷惑 <br>
 * 简单来说, LOWEST 优先级最高, 是第一个被触发的等级, MONITOR 优先级最低, 是最后一个被触发的等级 <br><br>
 * 1. LOWEST <br>
 * 2. LOW <br>
 * 3. NORMAL (default) <br>
 * 4. HIGH <br>
 * 5. HIGHEST <br>
 * 6. MONITOR <br>
 */
@SuppressWarnings("unused")
public enum EventPriority {
    LOWEST,
    LOW,
    NORMAL,
    HIGH,
    HIGHEST,
    MONITOR
}
