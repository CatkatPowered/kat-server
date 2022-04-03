package com.catkatpowered.katserver.network.packet;

import com.catkatpowered.katserver.common.constants.KatPacketTypeConstants;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
public class ErrorPacket {
    // 错误信息
    @SerializedName("error")
    String error;
    // 包类型
    String type = KatPacketTypeConstants.ERROR_PACKET;

    @Builder
    public ErrorPacket(String error) {
        this.error = error;
    }
}
