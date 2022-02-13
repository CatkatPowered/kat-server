package com.catkatpowered.katserver.storage;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.message.KatUniMessage;
import java.util.List;
import java.util.Optional;
import jdk.jfr.Experimental;
import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("unused")
@Experimental
@Slf4j
public class KatMessageStorage {

    /**
     * 用于查询消息记录，索引值为<b>KatUniMessage.messageID</b>
     *
     * @param indexMsg 对应数据库当中的索引
     * @return 返回被<b>Optional</b>包装的<b>List</b>类型
     * @see Optional
     */

    public static Optional<List<KatUniMessage>> getMessage(KatUniMessage indexMsg) {
        return Optional.ofNullable(
            KatServer.KatDatabaseAPI
                .getActions()
                .read(
                    KatServer.KatDatabaseAPI.getConnector().getConnection(),
                    indexMsg.messageGroup,
                    indexMsg)
        );
    }

    /**
     * 更新数据库当中的消息记录<br>
     *
     * @param oldContent 旧消息记录
     * @param newContent 新消息记录
     */
    public static void updateMessage(KatUniMessage oldContent, KatUniMessage newContent) {

        KatServer.KatDatabaseAPI.getActions().update(
            KatServer.KatDatabaseAPI.getConnector().getConnection(),
            oldContent.messageGroup,
            newContent
        );

    }
}
