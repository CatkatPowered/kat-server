package com.catkatpowered.katserver.message;

public class KatUniMessageTypeManager {

    public static boolean addMessageType(String msgType) {
        return KatUniMessageType.getInstance().addNewMessageType(msgType);
    }
}
