package com.catkatpowered.katserver.message;

import com.catkatpowered.katserver.common.KatMessageTypeConstants;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

/**
 * Kat 聚合消息
 *
 * @author suibing112233
 */
@Builder
@Data
public class KatUniMessage {

    @Default
    @SerializedName("message_type")
    public String messageType = KatMessageTypeConstants.KAT_MESSAGE_TYPE_PLAIN_MESSAGE;

    @SerializedName("message_id")
    public String messageID;

    @SerializedName("message_content")
    public ArrayList<String> messageContent;

    @SerializedName("message_list")
    public ArrayList<KatUniMessage> messageList;

    @SerializedName("extended")
    public ArrayList<String> extended;

    @SerializedName("media_hash")
    public String mediaHash;

    @SerializedName("media_name")
    public String mediaName;

    @SerializedName("media_url")
    public String mediaURL;

    public KatUniMessage(String messageType, String messageID, ArrayList<String> messageContent, ArrayList<KatUniMessage> messageList, ArrayList<String> extended, String mediaHash, String mediaName, String mediaURL) {
        this.messageType = messageType;
        this.messageID = messageID;
        this.messageContent = messageContent;
        this.messageList = messageList;
        this.extended = extended;
        this.mediaHash = mediaHash;
        this.mediaName = mediaName;
        this.mediaURL = mediaURL;
    }
}