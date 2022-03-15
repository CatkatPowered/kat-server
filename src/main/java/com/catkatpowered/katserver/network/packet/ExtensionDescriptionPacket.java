package com.catkatpowered.katserver.network.packet;

import com.catkatpowered.katserver.common.constants.KatPacketTypeConstants;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 用于描述扩展信息
 *
 * @author hanbings
 */
@Data
public class ExtensionDescriptionPacket {
    // 平台标识符
    @SerializedName("extension_id")
    String extensionId;
    // 数据包类型
    @SerializedName("type")
    private String type = KatPacketTypeConstants.EXTENSION_DESCRIPTION_PACKET;

    public ExtensionDescriptionPacket(String extensionId) {
        this.extensionId = extensionId;
    }
}
