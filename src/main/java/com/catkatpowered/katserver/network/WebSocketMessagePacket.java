package com.catkatpowered.katserver.network;

import com.catkatpowered.katserver.message.KatUniMessage;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
}
