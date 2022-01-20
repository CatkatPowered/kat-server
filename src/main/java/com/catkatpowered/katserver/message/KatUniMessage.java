package com.catkatpowered.katserver.message;

import com.catkatpowered.katserver.common.KatMessageTypeConstants;
import java.util.ArrayList;
import lombok.Builder;
import lombok.Builder.Default;

/**
 * Kat 聚合消息
 *
 * @author suibing112233
 */
@Builder
public class KatUniMessage {

    @Default
    public String messageType = KatMessageTypeConstants.KAT_MESSAGE_TYPE_PLAIN_MESSAGE;

    public String messageID;

    public ArrayList<Byte> messageContent;

    public ArrayList<KatUniMessage> messageList;

    public ArrayList<Byte> extended;

    public String mediaHash;

    public String mediaName;

    public String mediaURL;
}
