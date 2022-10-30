package com.catkatpowered.katserver.tokenpool;

public class KatTokenPoolManager {

  public static void init() {}

  public static String newToken() {
    return KatTokenPool.getINSTANCE().newToken();
  }

  public static boolean revokeToken(String tokeString) {
    return KatTokenPool.getINSTANCE().destroyToken(tokeString);
  }

  public static boolean checkToken(String token) {
    return KatTokenPool.getINSTANCE().checkToken(token);
  }

  public static KatTokenPool getInstance() {
    return KatTokenPool.getINSTANCE();
  }
}
