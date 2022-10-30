package com.catkatpowered.katserver.message;

import com.catkatpowered.katserver.common.constants.KatMessageTypeConstants;
import java.util.HashSet;
import lombok.Getter;
import lombok.Setter;

/**
 * Kat 消息类型管理器
 *
 * @author suibing112233
 */
public class KatUniMessageType {

  private static final KatUniMessageType Instance = new KatUniMessageType();

  @Getter
  @Setter
  private HashSet<String> messageTypes = new HashSet<>() {
    {
      add(KatMessageTypeConstants.KAT_MESSAGE_TYPE_FILE_MESSAGE);
      add(KatMessageTypeConstants.KAT_MESSAGE_TYPE_COLLECTION_MESSAGE);
      add(KatMessageTypeConstants.KAT_MESSAGE_TYPE_PLAIN_MESSAGE);
      add(KatMessageTypeConstants.KAT_MESSAGE_TYPE_AUDIO_MESSAGE);
      add(KatMessageTypeConstants.KAT_MESSAGE_TYPE_IMAGE_MESSAGE);
      add(KatMessageTypeConstants.KAT_MESSAGE_TYPE_VIDEO_MESSAGE);
      add(KatMessageTypeConstants.KAT_MESSAGE_TYPE_MIXED_MESSAGE);
    }
  };

  private KatUniMessageType() {}

  public static KatUniMessageType getInstance() {
    return Instance;
  }

  public boolean addNewMessageType(String msgType) {
    return this.messageTypes.add(msgType);
  }
}
