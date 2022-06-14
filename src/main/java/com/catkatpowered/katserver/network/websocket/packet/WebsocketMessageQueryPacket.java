package com.catkatpowered.katserver.network.websocket.packet;

import com.catkatpowered.katserver.common.constants.KatPacketTypeConstants;
import com.catkatpowered.katserver.message.KatUniMessage;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.util.List;

// 此数据包双向发送
// moseeger -> kat-server 时提供 extensionId,startTimeStamp,endTimeStamp,messageGroup
// kat-server -> moseeger 时返回原信息并填充 messages
@Data
@Builder
public class WebsocketMessageQueryPacket {
    // 平台标识符
    @SerializedName("extension_id")
    String extensionId;
    // 数组形式的消息组(返回时使用)
    @SerializedName("messages")
    List<KatUniMessage> messages;
    // 数据包类型
    @SerializedName("type")
    final String type = KatPacketTypeConstants.MESSAGE_QUERY_PACKET;
    // 查询起始时间戳
    @SerializedName("start_timestamp")
    Integer startTimeStamp;
    // 查询结束时间戳
    @SerializedName("end_timestamp")
    Integer endTimeStamp;
    // 查询平台下messagegroup标识
    @SerializedName("message_group")
    String messageGroup;


    public WebsocketMessageQueryPacket(String extensionId, List<KatUniMessage> messages, Integer startTimeStamp, Integer endTimeStamp, String messageGroup) {
        this.extensionId = extensionId;
        this.messages = messages;
        this.startTimeStamp = startTimeStamp;
        this.endTimeStamp = endTimeStamp;
        this.messageGroup = messageGroup;
    }

}
