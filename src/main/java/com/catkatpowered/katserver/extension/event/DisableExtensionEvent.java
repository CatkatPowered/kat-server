package com.catkatpowered.katserver.extension.event;

import com.catkatpowered.katserver.extension.KatExtensionInfo;

public class DisableExtensionEvent extends ExtensionEvent {

  public DisableExtensionEvent(KatExtensionInfo extensionInfo) {
    super(extensionInfo);
  }
}
