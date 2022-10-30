package com.catkatpowered.katserver.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.jetbrains.annotations.NotNull;

/**
 * Sha256 辅助工具类。
 * <p>
 * KatServer 内部默认并且推荐使用本工具计算 Hash，包括并且不限于以下内容使用本工具类
 *
 * <li>{@link com.catkatpowered.katserver.storage.providers.KatResource#hash}</li>
 *
 * @author hanbings
 * @author Krysztal
 */
@SuppressWarnings("unused")
public class KatShaUtils {

  /**
   * 计算文件的 {@code Sha256}
   *
   * @param file 目标文件
   * @return {@code Sha256} 计算结果
   */
  public static String sha256(File file) {
    if (file == null || file.length() == 0) {
      return null;
    }
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("Sha256");
      FileInputStream fileInputStream = new FileInputStream(file);
      return sumSha256(fileInputStream, messageDigest);
    } catch (IOException | NoSuchAlgorithmException exception) {
      exception.printStackTrace();
    }
    return null;
  }

  /**
   * 计算输入流的 {@code Sha256}
   *
   * @param inputStream 目标输入流
   * @return {@code Sha256} 计算结果
   */
  public static String sha256(InputStream inputStream) {
    if (inputStream == null) {
      return null;
    }
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("Sha256");
      return sumSha256(inputStream, messageDigest);
    } catch (IOException | NoSuchAlgorithmException exception) {
      exception.printStackTrace();
    }
    return null;
  }

  /**
   * 计算字节数组的 {@code Sha256}
   *
   * @param bytes 目标字节
   * @return {@code Sha256} 计算结果
   */
  public static String sha256(byte[] bytes) {
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("Sha256");
      messageDigest.update(bytes);
      byte[] byteArray = messageDigest.digest();
      StringBuilder stringBuilder = new StringBuilder();
      for (byte temp : byteArray) {
        stringBuilder.append(String.format("%02x", temp));
      }
      return stringBuilder.toString();
    } catch (NoSuchAlgorithmException exception) {
      exception.printStackTrace();
    }
    return null;
  }

  @NotNull
  private static String sumSha256(
    InputStream inputStream,
    MessageDigest messageDigest
  ) throws IOException {
    byte[] buffer = new byte[1024];
    int len;
    while ((len = inputStream.read(buffer)) != -1) {
      messageDigest.update(buffer, 0, len);
    }
    inputStream.close();
    byte[] byteArray = messageDigest.digest();
    StringBuilder stringBuilder = new StringBuilder();
    for (byte temp : byteArray) {
      stringBuilder.append(String.format("%02x", temp));
    }
    return stringBuilder.toString();
  }
}
