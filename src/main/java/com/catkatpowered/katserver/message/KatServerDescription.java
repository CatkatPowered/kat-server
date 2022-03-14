package com.catkatpowered.katserver.message;

import com.catkatpowered.katserver.common.constants.KatMessageTypeConstants;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

/**
 * 用于描述服务器的信息
 *
 * @author hanbings
 */
@Data
@Builder
public class KatServerDescription {
    // 消息类型
    @SerializedName("message_type")
    String messageType = KatMessageTypeConstants.KAT_MESSAGE_TYPE_SERVER_DESCRIPTION_MESSAGE;
    // 服务器名称
    @SerializedName("server")
    String server;
    // 服务器版本
    @SerializedName("version")
    String version;
    // 服务器描述
    @SerializedName("description")
    String description;
}
