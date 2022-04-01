package com.catkatpowered.katserver.event.events;

import com.catkatpowered.katserver.event.Event;
import com.catkatpowered.katserver.message.KatUniMessage;
import lombok.Builder;
import lombok.Data;

@Data
public class MessageReceiveEvent extends Event {
    String extensionId;
    KatUniMessage message;
    @Builder
    public MessageReceiveEvent(String extensionId, KatUniMessage message) {
        this.extensionId = extensionId;
        this.message = message;
    }
}
