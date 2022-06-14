package com.catkatpowered.katserver.network.websocket.packet;

import com.catkatpowered.katserver.common.constants.KatPacketTypeConstants;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorPacket {
    // 错误信息
    @SerializedName("error")
    String error;
    // 包类型
    final String type = KatPacketTypeConstants.ERROR_PACKET;


    public ErrorPacket(String error) {
        this.error = error;
    }
}
