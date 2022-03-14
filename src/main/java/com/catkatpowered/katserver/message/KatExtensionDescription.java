package com.catkatpowered.katserver.message;

import com.catkatpowered.katserver.common.constants.KatMessageTypeConstants;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

/**
 * 用于描述扩展信息
 *
 * @author hanbings
 */
@Data
@Builder
public class KatExtensionDescription {
    // 消息类型
    @SerializedName("message_type")
    String messageType = KatMessageTypeConstants.KAT_MESSAGE_TYPE_EXTENSION_DESCRIPTION_MESSAGE;
}
