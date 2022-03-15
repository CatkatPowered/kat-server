package com.catkatpowered.katserver.network.packet;

import com.catkatpowered.katserver.common.constants.KatPacketTypeConstants;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

/**
 * 用于描述凭据
 *
 * @author hanbings
 */
@Data
public class HttpResourceTokenPacket {
    // 凭据数据包
    @SerializedName("type")
    String type = KatPacketTypeConstants.RESOURCE_TOKEN_PACKET;
    // 凭据
    @SerializedName("token")
    String token;
    // 过期时间 long 类型时间戳
    @SerializedName("expire_time")
    long expireTime;
    // 有效 websocket sec key
    @SerializedName("websocket_sec_key")
    String websocketSecKey;

    @Builder
    public HttpResourceTokenPacket(String token, long expireTime, String websocketSecKey) {
        this.token = token;
        this.expireTime = expireTime;
        this.websocketSecKey = websocketSecKey;
    }
}
