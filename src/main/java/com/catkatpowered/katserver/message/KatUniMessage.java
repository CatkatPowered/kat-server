package com.catkatpowered.katserver.message;

import com.catkatpowered.katserver.common.KatMessageTypeConstants;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import lombok.Data;

/**
 * Kat 聚合消息
 *
 * @author suibing112233
 */
@Data
public class KatUniMessage {

    @SerializedName("message_type")
    public String messageType = KatMessageTypeConstants.KAT_MESSAGE_TYPE_PLAIN_MESSAGE;

    @SerializedName("message_id")
    public String messageID;

    @SerializedName("message_content")
    public ArrayList<Byte> messageContent;

    @SerializedName("message_list")
    public ArrayList<KatUniMessage> messageList;

    @SerializedName("extended")
    public ArrayList<Byte> extended;

    @SerializedName("media_hash")
    public String mediaHash;

    @SerializedName("media_name")
    public String mediaName;

    @SerializedName("media_url")
    public String mediaURL;
}
