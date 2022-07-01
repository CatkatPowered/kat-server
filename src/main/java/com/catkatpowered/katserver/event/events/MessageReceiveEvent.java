package com.catkatpowered.katserver.event.events;

import com.catkatpowered.katserver.event.Event;
import com.catkatpowered.katserver.message.KatUniMessage;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class MessageReceiveEvent extends Event {
    KatUniMessage message;
}
