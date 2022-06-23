package com.catkatpowered.katserver.network.websocket.packet;

import lombok.Data;

// BasePacket只被用来继承
@Data
public class BasePacket {
    String type;
    public BasePacket(String type) {
        this.type = type;
    }

}
