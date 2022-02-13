package com.catkatpowered.katserver.message;

import com.catkatpowered.katserver.common.constants.KatMessageTypeConstants;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Objects;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

/**
 * Kat 聚合消息
 *
 * @author suibing112233
 * @author hanbings
 */
@Data
@Builder
public class KatUniMessage {

    /**
     * <b>MessageType</b> 用于区分本级的消息类型，方便前端处理消息的展示以及<em>KatServer</em>对消息的储存和处理。<br>
     * <p>
     * 其中为了方便<em>Extension</em>的识别，这里采用的是字符串储存而不是<b>enum</b>。 <br>
     * <p>
     * 同时提供了<b>KatUniMessageTypeManager</b>方便<em>Extension</em>拓展消息的类型。
     *
     * @see KatMessageTypeConstants
     * @see KatUniMessageTypeManager
     */
    @SerializedName("message_type")
    @Default
    public String messageType = KatMessageTypeConstants.KAT_MESSAGE_TYPE_PLAIN_MESSAGE;

    /**
     * <b>MessageIdentity</b> 用于本条消息的聚合特征，方便消息存入库处理
     * </p>
     */
    @SerializedName("message_identity")
    public String messageIdentity;

    /**
     * <b>MessageID</b> 是本级消息的唯一标识符，方便<em>Extension</em>对本层消息的处理以及<em>KatServer</em>对本层消息的存取。</br>
     * <p>
     * <b>MessageID</b> 由<em>KatServer</em>提供算法进行分配。暂定为<em>UUID</em></br>
     */
    @SerializedName("message_id")
    public String messageID;

    /**
     * <b>MessageContent</b> 是本级消息的内容。<br>
     */
    @SerializedName("message_content")
    public String messageContent;

    /**
     * <b>MessageList</b> 指向的是下一级消息列表。</br>
     * <p>
     * 当前假设情况如下</br>
     * <ol>
     *     <li>如果<b>MessageType</b>为<b>KAT_MESSAGE_TYPE_MIXED_MESSAGE</b>，则<b>MessageList</b>中为顺序化的呈现</li>
     *     <li>如果<b>MessageType</b>为<b>KAT_MESSAGE_TYPE_COLLECTION_MESSAGE</b>，则<b>MessageList</b>中为消息转发消息的列表</li>
     * </ol>
     */
    @SerializedName("message_list")
    public ArrayList<KatUniMessage> messageList;

    /**
     * <b>Extended</b> 是<em>Extension</em>对消息的额外补充内容</br>
     * <p>
     * <em>KatServer</em>对这部分消息是不敏感的，也就是说并不会对这段内容进行额外的补充
     */
    @SerializedName("extended")
    public ArrayList<String> extended;

    /**
     * <b>ResourceHash</b> 是资源文件的哈希值
     */
    @SerializedName("resource_hash")
    public String resourceHash;

    /**
     * <b>ResourceName</b> 为资源文件的实际名称
     */
    @SerializedName("resource_name")
    public String resourceName;

    /**
     * <b>ResourceURL</b> 为资源文件的下载地址
     */
    @SerializedName("resource_url")
    public String resourceURL;

    public KatUniMessage(
        String messageType,
        String messageIdentity,
        String messageID,
        String messageContent,
        ArrayList<KatUniMessage> messageList,
        ArrayList<String> extended,
        String resourceHash,
        String resourceName,
        String resourceURL) {
        this.messageType = messageType;
        this.messageIdentity = messageIdentity;
        this.messageID = messageID;
        this.messageContent = messageContent;
        this.messageList = messageList;
        this.extended = extended;
        this.resourceHash = resourceHash;
        this.resourceName = resourceName;
        this.resourceURL = resourceURL;
    }

    /**
     * 判断是否包含资源信息<br>
     * <p>
     * 当前的条件为当<b>MessageType</b>等于以下项目<br>
     * <ol>
     *     <li><b>KAT_MESSAGE_TYPE_FILE_MESSAGE</b></li>
     *     <li><b>KAT_MESSAGE_TYPE_IMAGE_MESSAGE</b></li>
     *     <li><b>KAT_MESSAGE_TYPE_AUDIO_MESSAGE</b></li>
     *     <li><b>KAT_MESSAGE_TYPE_VIDEO_MESSAGE</b></li>
     * </ol>
     * <p>
     * 任意之一时则成立
     *
     * @return 当包含资源类信息时，返回<b>true</b>，否则返回<b>false</b>
     */
    public boolean isResource() {
        return
            Objects.equals(this.messageType, KatMessageTypeConstants.KAT_MESSAGE_TYPE_FILE_MESSAGE)
                || Objects.equals(this.messageType,
                KatMessageTypeConstants.KAT_MESSAGE_TYPE_IMAGE_MESSAGE)
                || Objects.equals(this.messageType,
                KatMessageTypeConstants.KAT_MESSAGE_TYPE_AUDIO_MESSAGE)
                || Objects.equals(this.messageType,
                KatMessageTypeConstants.KAT_MESSAGE_TYPE_VIDEO_MESSAGE);
    }

    public boolean isDownloadable() {
        return this.resourceURL != null;
    }

    public boolean isHashed() {
        return this.resourceHash != null;
    }

    public boolean isIdentified() {
        return this.messageIdentity != null;
    }
}