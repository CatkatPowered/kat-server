package com.catkatpowered.katserver.network.websocket.packet;

import com.catkatpowered.katserver.common.constants.KatPacketTypeConstants;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomPacket{
    @SerializedName("type")
    private final String type = KatPacketTypeConstants.CUSTOM_PACKET;
    // 扩展名，用于将包传递给相应的扩展处理
    @SerializedName("extension_id")
    String extensionId;
    @SerializedName("content")
    Object content;
}
