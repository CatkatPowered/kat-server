package com.catkatpowered.katserver.network.packet;

import com.catkatpowered.katserver.common.constants.KatPacketTypeConstants;
import com.catkatpowered.katserver.message.KatUniMessage;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

// 此数据包双向发送
// moseeger -> kat-server 时提供 extensionId,startTimeStamp,endTimeStamp,messageGroup
// kat-server -> moseeger 时返回原信息并填充 messages
@Data
public class WebsocketMessageQueryPacket {
    // 平台标识符
    @SerializedName("extension_id")
    String extensionId;
    // 数组形式的消息组
    @SerializedName("messages")
    KatUniMessage[] messages;
    // 数据包类型
    @SerializedName("type")
    String type = KatPacketTypeConstants.MESSAGE_QUERY_PACKET;
    // 查询起始时间戳
    @SerializedName("start_timestamp")
    Integer startTimeStamp;
    // 查询结束时间戳
    @SerializedName("end_timestamp")
    Integer endTimeStamp;
    // 查询平台下messagegroup标识
    @SerializedName("message_group")
    String messageGroup;

    @Builder
    public WebsocketMessageQueryPacket(String extensionId, KatUniMessage[] messages, Integer startTimeStamp, Integer endTimeStamp, String messageGroup){
        this.extensionId = extensionId;
        this.messages = messages;
        this.startTimeStamp = startTimeStamp;
        this.endTimeStamp = endTimeStamp;
        this.messageGroup = messageGroup;
    }

}
