package com.catkatpowered.katserver.network.packet;

import com.catkatpowered.katserver.common.constants.KatPacketTypeConstants;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

/**
 * 用于描述服务器的信息
 *
 * @author hanbings
 */
@Data
public class ServerDescriptionPacket {
    // 服务器名称
    @SerializedName("server")
    String server;
    // 服务器版本
    @SerializedName("version")
    String version;
    // 服务器描述
    @SerializedName("description")
    String description;
    // 数据包类型
    @SerializedName("type")
    private String type = KatPacketTypeConstants.SERVER_DESCRIPTION_PACKET;

    @Builder
    public ServerDescriptionPacket(String server, String version, String description) {
        this.server = server;
        this.version = version;
        this.description = description;
    }
}
