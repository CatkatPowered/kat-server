package com.catkatpowered.katserver.extension.event;

import com.catkatpowered.katserver.event.Event;
import com.catkatpowered.katserver.extension.KatExtensionInfo;
import lombok.Getter;

public class ExtensionEvent extends Event {

  @Getter
  KatExtensionInfo extensionInfo;

  public ExtensionEvent(KatExtensionInfo extensionInfo) {
    this.extensionInfo = extensionInfo;
  }
}
