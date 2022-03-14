package com.catkatpowered.katserver.message;

import com.catkatpowered.katserver.common.constants.KatMessageTypeConstants;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

/**
 * Kat 消息类型管理器
 *
 * @author suibing112233
 */
public class KatUniMessageType {

    private static final KatUniMessageType Instance = new KatUniMessageType();

    @Getter
    @Setter
    private HashSet<String> messageTypes = new HashSet<>();

    private KatUniMessageType() {
        this.messageTypes.add(KatMessageTypeConstants.KAT_MESSAGE_TYPE_FILE_MESSAGE);
        this.messageTypes.add(KatMessageTypeConstants.KAT_MESSAGE_TYPE_COLLECTION_MESSAGE);
        this.messageTypes.add(KatMessageTypeConstants.KAT_MESSAGE_TYPE_PLAIN_MESSAGE);
        this.messageTypes.add(KatMessageTypeConstants.KAT_MESSAGE_TYPE_AUDIO_MESSAGE);
        this.messageTypes.add(KatMessageTypeConstants.KAT_MESSAGE_TYPE_IMAGE_MESSAGE);
        this.messageTypes.add(KatMessageTypeConstants.KAT_MESSAGE_TYPE_VIDEO_MESSAGE);
        this.messageTypes.add(KatMessageTypeConstants.KAT_MESSAGE_TYPE_MIXED_MESSAGE);
        this.messageTypes.add(KatMessageTypeConstants.KAT_MESSAGE_TYPE_EXTENSION_DESCRIPTION_MESSAGE);
        this.messageTypes.add(KatMessageTypeConstants.KAT_MESSAGE_TYPE_SERVER_DESCRIPTION_MESSAGE);
        this.messageTypes.add(KatMessageTypeConstants.KAT_MESSAGE_TYPE_RESOURCE_TOKEN_MESSAGE);
    }

    public static KatUniMessageType getInstance() {
        return Instance;
    }

    public boolean addNewMessageType(String msgType) {
        return this.messageTypes.add(msgType);
    }
}
