package com.catkatpowered.katserver.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.catkatpowered.katserver.database.mongodb.MongodbConnection;
import com.catkatpowered.katserver.database.mongodb.MongodbConnector;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

@SuppressWarnings("SpellCheckingInspection")
public class TestMongoDBDatabase {

  @AllArgsConstructor
  static class Data {

    String message;
    int number;
    boolean flag;
    HashMap<String, String> map;
    String[] array;
  }

  @Test
  public void connect() {
    // 生成连接器
    MongodbConnector connector = new MongodbConnector(
      "mongodb://localhost:27017/kat-server",
      null,
      null
    );
    // 连接
    connector.open();
    // 获取连接
    MongodbConnection connection = (MongodbConnection) connector.connection();

    // 写入数据
    connection.create(
      "test",
      new Data(
        "点一份炒饭",
        1919810,
        true,
        new HashMap<>() {
          {
            put("锟斤拷", "烫烫烫");
          }
        },
        new String[] { "1", "1", "4", "5", "1", "4" }
      )
    );
    // 读取数据
    List<Data> data = connection.read(
      "test",
      new HashMap<>() {
        {
          put("message", "点一份炒饭");
        }
      },
      Data.class
    );
    // 测试数据
    assertEquals("点一份炒饭", data.get(0).message);
    assertEquals(1919810, data.get(0).number);
    assertTrue(data.get(0).flag);
    assertEquals("烫烫烫", data.get(0).map.get("锟斤拷"));
    assertEquals("1", data.get(0).array[0]);

    // 删除数据
    connection.delete(
      "test",
      new HashMap<>() {
        {
          put("message", "点一份炒饭");
        }
      }
    );
    // 验证删除
    data =
      connection.read(
        "test",
        new HashMap<>() {
          {
            put("message", "点一份炒饭");
          }
        },
        Data.class
      );
    assertEquals(0, data.size());

    // search 方法查询数据
    IntStream
      .range(114514, 114517)
      .forEach(count -> {
        connection.create(
          "test",
          new Data(
            "点一份炒饭",
            count,
            true,
            new HashMap<>() {
              {
                put("锟斤拷", "烫烫烫");
              }
            },
            new String[] { "1", "1", "4", "5", "1", "4" }
          )
        );
      });

    // 查询数据
    data = connection.search("test", "number", 114514, 114516, 10, Data.class);
    // 测试数据
    assertEquals(3, data.size());
  }
}
