package com.catkatpowered.katserver.extension.event;

import com.catkatpowered.katserver.extension.KatExtensionInfo;

public class EnableExtensionEvent extends ExtensionEvent {
    public EnableExtensionEvent(KatExtensionInfo extensionInfo) {
        super(extensionInfo);
    }
}
