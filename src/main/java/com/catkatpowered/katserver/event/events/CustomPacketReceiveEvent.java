package com.catkatpowered.katserver.event.events;

import com.catkatpowered.katserver.event.Event;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class CustomPacketReceiveEvent extends Event {
    String extensionId;
    Object content;
}
