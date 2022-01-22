package com.catkatpowered.katserver.message;

import com.catkatpowered.katserver.common.KatMessageTypeConstants;
import java.util.HashSet;
import lombok.Getter;
import lombok.Setter;

/**
 * Kat 消息类型管理器
 *
 * @author suibing112233
 */
public class KatMessageType {

    private static final KatMessageType Instance = new KatMessageType();

    @Getter @Setter
    private HashSet<String> messageTypes = new HashSet<>();

    private KatMessageType() {
        this.messageTypes.add(KatMessageTypeConstants.KAT_MESSAGE_TYPE_FILE_MESSAGE);
        this.messageTypes.add(KatMessageTypeConstants.KAT_MESSAGE_TYPE_MEDIA_MESSAGE);
        this.messageTypes.add(KatMessageTypeConstants.KAT_MESSAGE_TYPE_COLLECTION_MESSAGE);
        this.messageTypes.add(KatMessageTypeConstants.KAT_MESSAGE_TYPE_PLAIN_MESSAGE);

    }

    public static KatMessageType getInstance() {
        return Instance;
    }


}
