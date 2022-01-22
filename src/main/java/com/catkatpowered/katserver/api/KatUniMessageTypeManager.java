package com.catkatpowered.katserver.api;

import com.catkatpowered.katserver.message.KatMessageType;

public class KatUniMessageTypeManager {

    public static boolean addNewMessageType(String msgType) {
        var msgTypes = KatMessageType.getInstance().getMessageTypes();
        if (msgTypes.add(msgType)) {
            KatMessageType.getInstance().setMessageTypes(msgTypes);
            return true;
        }
        return false;
    }
}
