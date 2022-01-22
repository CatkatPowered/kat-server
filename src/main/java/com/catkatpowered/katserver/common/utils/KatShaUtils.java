package com.catkatpowered.katserver.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * sha256 辅助工具类
 *
 * @author hanbings
 */
public class KatShaUtils {
    public String sha256(File file) {
        if (file == null || file.length() == 0) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("Sha256");
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fileInputStream.read(buffer)) != -1) {
                messageDigest.update(buffer, 0, len);
            }
            fileInputStream.close();
            byte[] byteArray = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte temp : byteArray) {
                stringBuilder.append(String.format("%02x", temp));
            }
            return stringBuilder.toString();
        } catch (IOException | NoSuchAlgorithmException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public String sha256(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("Sha256");
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
        } catch (IOException | NoSuchAlgorithmException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public String sha256(byte[] bytes) {
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
}
