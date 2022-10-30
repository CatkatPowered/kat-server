package com.catkatpowered.katserver.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * <em>KatServer</em> 提供的标准下载工具
 *
 * @author Krysztal
 * @see OkHttpClient
 */
public class KatDownload {

  public static InputStream getDownloadFileStream(
    File file,
    URL url,
    Map<String, String> headers
  ) throws IOException {
    var client = new OkHttpClient();
    var downloadRequestBuilder = new Request.Builder().url(url);

    if (headers != null) for (var i : headers.keySet()) {
      downloadRequestBuilder.addHeader(i, headers.get(i));
    }

    var downloadRequest = downloadRequestBuilder.build();
    var call = client.newCall(downloadRequest);

    return call.execute().body().byteStream();
  }

  public static void writeFile(File path, InputStream inputStream) {
    var file = new KatFile(path.getAbsolutePath());

    file.lock();

    // Buffer
    var len = 0;
    var buffer = new byte[2048];

    try {
      var fileOutputStream = new FileOutputStream(path);

      while ((len = inputStream.read(buffer)) != -1) {
        fileOutputStream.write(buffer, 0, len);
      }

      fileOutputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
      file.delete();
    } finally {
      file.unlock();
    }
  }
}
