package com.catkatpowered.katserver.event.events;

import com.catkatpowered.katserver.event.Event;
import com.catkatpowered.katserver.message.KatUniMessage;
import lombok.Builder;
import lombok.Data;

@Data
public class MessageSendEvent extends Event {
    private static String extensionId;
    private static KatUniMessage message;
    @Builder
    public MessageSendEvent(String extensionId, KatUniMessage message) {
        this.extensionId = extensionId;
        this.message = message;
    }
}
