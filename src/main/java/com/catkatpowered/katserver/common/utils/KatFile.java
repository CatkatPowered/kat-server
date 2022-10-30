package com.catkatpowered.katserver.common.utils;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class KatFile extends File {

  public KatFile(File parent, String child) {
    super(parent, child);
  }

  public KatFile(String pathname) {
    super(pathname);
  }

  public KatFile(String parent, String child) {
    super(parent, child);
  }

  public KatFile(URI uri) {
    super(uri);
  }

  public boolean lock() {
    try {
      (new File(super.getAbsolutePath() + ".lock")).createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public boolean unlock() {
    return (new File(super.getAbsolutePath() + ".lock")).delete();
  }
}
