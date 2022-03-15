package com.catkatpowered.katserver.network.packet;

import com.catkatpowered.katserver.common.constants.KatPacketTypeConstants;
import com.catkatpowered.katserver.message.KatUniMessage;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
public class WebSocketMessagePacket {
    /*
    {
        "extension_id": "discord",
        "message": {}
    }
     */
    String boundTo;
    // 平台标识符
    @SerializedName("extension_id")
    String extensionId;
    // 消息本体
    @SerializedName("message")
    KatUniMessage message;
    // 数据包类型
    @SerializedName("type")
    private String type = KatPacketTypeConstants.MESSAGE_PACKET;

    @Builder
    public WebSocketMessagePacket(String boundTo, String extensionId, KatUniMessage message) {
        this.boundTo = boundTo;
        this.extensionId = extensionId;
        this.message = message;
    }
}
