package com.catkatpowered.katserver.tokenpool;

import static org.junit.jupiter.api.Assertions.*;

import com.catkatpowered.katserver.config.KatConfig;
import com.catkatpowered.katserver.tokenpool.KatTokenPool.ClearAction;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.tomlj.Toml;
import org.tomlj.TomlParseResult;

public class TestKatTokenPool {

  static String defaultConfig =
    """
                [tokenpool]
                # You can set the token how long to live
                #
                # Unit: Millisecond
                outdated = 10000
            """;

  @BeforeAll
  public static void initTest() {
    Map<String, Object> config = new HashMap<>();
    TomlParseResult toml = Toml.parse(defaultConfig);
    for (Map.Entry<String, Object> entry : toml.dottedEntrySet()) {
      config.put(entry.getKey(), entry.getValue());
    }
    KatConfig.getInstance().setConfigContent(config);
  }

  @Test
  public void newToken() {
    var token = KatTokenPool.getINSTANCE().newToken();
    assertTrue(KatTokenPool.getINSTANCE().checkToken(token));
  }

  @Test
  public void cleanTokensForClearAll() {
    KatTokenPool.getINSTANCE().newToken();
    KatTokenPool.getINSTANCE().newToken();
    KatTokenPool.getINSTANCE().newToken();
    KatTokenPool.getINSTANCE().newToken();
    KatTokenPool.getINSTANCE().newToken();

    KatTokenPool.getINSTANCE().cleanTokens(ClearAction.All);

    assertEquals(0, KatTokenPool.getINSTANCE().getTokenPool().size());
  }

  @Test
  public void cleanTokensForClearOutdated() {
    var token1 = KatTokenPool.getINSTANCE().newToken(); // -10s | Outdated
    var token2 = KatTokenPool.getINSTANCE().newToken(); // -5s |
    var token3 = KatTokenPool.getINSTANCE().newToken(); // +0s |
    var token4 = KatTokenPool.getINSTANCE().newToken(); // +5s |
    var token5 = KatTokenPool.getINSTANCE().newToken(); // +10s |

    var pool = KatTokenPool.getINSTANCE().getTokenPool();

    pool.put(token1, pool.get(token1) - 10 * 1000);
    pool.put(token2, pool.get(token2) - 5 * 1000);
    pool.put(token3, pool.get(token3) + 0 * 1000);
    pool.put(token4, pool.get(token4) + 5 * 1000);
    pool.put(token5, pool.get(token5) + 10 * 1000);

    KatTokenPool.getINSTANCE().setTokenPool(pool);

    KatTokenPool.getINSTANCE().cleanTokens(ClearAction.Outdated);

    assertEquals(4, KatTokenPool.getINSTANCE().getTokenPool().size());
  }

  @Test
  public void destroyToken() {
    var token = KatTokenPool.getINSTANCE().newToken();

    assertTrue(KatTokenPool.getINSTANCE().getTokenPool().containsKey(token));
    KatTokenPool.getINSTANCE().destroyToken(token);
    assertFalse(KatTokenPool.getINSTANCE().getTokenPool().containsKey(token));
  }
}
