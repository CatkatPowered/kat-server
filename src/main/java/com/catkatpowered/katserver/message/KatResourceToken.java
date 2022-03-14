package com.catkatpowered.katserver.message;

import com.catkatpowered.katserver.common.constants.KatMessageTypeConstants;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

/**
 * 用于描述凭据
 *
 * @author hanbings
 */
@Data
@Builder
public class KatResourceToken {
    // 消息类型
    @SerializedName("message_type")
    String messageType = KatMessageTypeConstants.KAT_MESSAGE_TYPE_RESOURCE_TOKEN_MESSAGE;
    // 凭据
    @SerializedName("token")
    String token;
    // 过期时间 long 类型时间戳
    @SerializedName("expire_time")
    long expireTime;
    // 有效 websocket sec key
    @SerializedName("websocket_sec_key")
    String websocketSecKey;
}
