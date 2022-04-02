package com.catkatpowered.katserver.event;

import com.catkatpowered.katserver.event.interfaces.EventHandler;
import com.catkatpowered.katserver.event.interfaces.Listener;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEventBus {
    @Test
    public void event() {
        // 初始化事件总线
        KatEventManager.init();
        // 获取事件总线
        EventBus eventBus = KatEventManager.getEventBus();
        // 注册事件
        TestEvent event = new TestEvent("Hello World!");
        TestVoidEvent voidEvent = new TestVoidEvent();
        eventBus.registerEvent(event);
        eventBus.registerEvent(voidEvent);
        // 注册监听器
        eventBus.registerListener(new TestListener());
        // 发布事件
        eventBus.callEvent(event);
    }

    static class TestEvent extends Event {
        @Setter
        @Getter
        String message;

        public TestEvent(String message) {
            this.message = message;
        }
    }

    static class TestVoidEvent extends Event {
        public TestVoidEvent() {

        }
    }

    public static class TestListener implements Listener {

        // 测试基本监听
        @EventHandler
        public void onTestEvent(TestEvent event) {
            assertEquals("Hello World!", event.getMessage());
        }

        // 测试事件是否泄漏
        @EventHandler
        public void onTestVoidEvent(TestVoidEvent event) throws Exception {
            throw new Exception();
        }
    }
}
