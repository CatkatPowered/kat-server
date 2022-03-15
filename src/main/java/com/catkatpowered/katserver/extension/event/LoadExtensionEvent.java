package com.catkatpowered.katserver.extension.event;

import com.catkatpowered.katserver.extension.KatExtension;
import com.catkatpowered.katserver.extension.KatExtensionInfo;

public class LoadExtensionEvent extends ExtensionEvent {
    public LoadExtensionEvent(KatExtensionInfo extensionInfo) {
        super(extensionInfo);
    }
}
