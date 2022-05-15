package com.catkatpowered.katserver.storage;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.message.KatUniMessage;
import jdk.jfr.Experimental;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@Experimental
@Slf4j
public class KatMessageStorage {

    public static Optional<List<KatUniMessage>> searchMessage(String extensionID, String messageGroup, Integer startTimeStamp, Integer endTimeStamp) {
        return Optional.ofNullable(
                KatServer.KatDatabaseAPI
                        .search(extensionID + "_" + messageGroup,
                                "message_timestamp",
                                startTimeStamp,
                                endTimeStamp,
                                // TODO: 商议消息数限制
                                20,
                                KatUniMessage.class
                        )
        );
    }

    /**
     * 用于查询单条消息记录，索引值为<b>KatUniMessage.messageID</b>
     *
     * @param indexMsg 对应数据库当中的索引
     * @return 返回被<b>Optional</b>包装的<b>List</b>类型
     * @see Optional
     */
    public static Optional<List<KatUniMessage>> getMessage(@NotNull KatUniMessage indexMsg) {
        return Optional.ofNullable(
                KatServer.KatDatabaseAPI
                        .read(indexMsg.extensionID + "_" + indexMsg.getMessageGroup(),
                                new HashMap<String, Object>() {{
                                    put("messageID", indexMsg.getMessageID());
                                }},
                                KatUniMessage.class)
        );
    }

    /**
     * 更新数据库当中的消息记录<br>
     *
     * @param oldContent 旧消息记录
     * @param newContent 新消息记录
     */
    public static void updateMessage(@NotNull KatUniMessage oldContent, KatUniMessage newContent) {
        KatServer.KatDatabaseAPI.update(oldContent.extensionID + "_" + oldContent.getMessageGroup(),
                new HashMap<String, Object>() {{
                    put("message_id", oldContent.getMessageID());
                }},
                newContent
        );
    }

    /**
     * 删除消息记录
     *
     * @param indexMsg 必须包含<b>KatUniMessage.messageGroup</b>和<b>KatUniMessage.messageID</b>
     */
    public static void deleteMessage(@NotNull KatUniMessage indexMsg) {
        if (indexMsg.isFullIndex()) {
            KatServer.KatDatabaseAPI.delete(indexMsg.extensionID + "_" + indexMsg.getMessageGroup(),
                    new HashMap<String, Object>() {{
                        put("message_id", indexMsg.getMessageID());
                    }});
        }
    }

    /**
     * 存放新的消息
     *
     * @param indexMsg 准备存放的信息
     */
    public static void createMessage(@NotNull KatUniMessage indexMsg) {
        if (indexMsg.isFullIndex()) {
            KatServer.KatDatabaseAPI.create(indexMsg.extensionID + "_" + indexMsg.getMessageGroup(), indexMsg);
        }
    }
}
