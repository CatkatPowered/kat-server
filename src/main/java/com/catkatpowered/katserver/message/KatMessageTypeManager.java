package com.catkatpowered.katserver.message;

import com.catkatpowered.katserver.common.KatMessageTypeConstants;
import java.util.HashSet;
import lombok.Getter;

/**
 * Kat 消息类型管理器
 *
 * @author suibing112233
 */
public class KatMessageTypeManager {

    private static final KatMessageTypeManager Instance = new KatMessageTypeManager();

    @Getter
    private final HashSet<String> messageTypes = new HashSet<>();

    private KatMessageTypeManager() {
        this.messageTypes.add(KatMessageTypeConstants.KAT_MESSAGE_TYPE_FILE_MESSAGE);
        this.messageTypes.add(KatMessageTypeConstants.KAT_MESSAGE_TYPE_MEDIA_MESSAGE);
        this.messageTypes.add(KatMessageTypeConstants.KAT_MESSAGE_TYPE_COLLECTION_MESSAGE);
        this.messageTypes.add(KatMessageTypeConstants.KAT_MESSAGE_TYPE_PLAIN_MESSAGE);

    }

    public static KatMessageTypeManager getInstance() {
        return Instance;
    }

    public boolean addNewMessageType(String msgType) {
        return this.messageTypes.add(msgType);
    }


}
