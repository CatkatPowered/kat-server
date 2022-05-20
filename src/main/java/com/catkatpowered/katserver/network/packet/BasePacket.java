package com.catkatpowered.katserver.network.packet;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
public class BasePacket {
    @SerializedName("type")
    String type;

    @Builder
    public BasePacket(String type) {
        this.type = type;
    }
}
