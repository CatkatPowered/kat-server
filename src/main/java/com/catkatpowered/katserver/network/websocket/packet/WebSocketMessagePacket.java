package com.catkatpowered.katserver.network.websocket.packet;

import com.catkatpowered.katserver.common.constants.KatPacketTypeConstants;
import com.catkatpowered.katserver.message.KatUniMessage;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WebSocketMessagePacket {

  /*
    {
        "type": "websocket_message",
        "message": {}
    }
     */
  // 消息本体
  @SerializedName("message")
  KatUniMessage message;

  // 数据包类型
  @SerializedName("type")
  final String type = KatPacketTypeConstants.MESSAGE_PACKET;

  public WebSocketMessagePacket(KatUniMessage message) {
    this.message = message;
  }
}
