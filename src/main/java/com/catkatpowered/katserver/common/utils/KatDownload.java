package com.catkatpowered.katserver.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class KatDownload {
    public static boolean isFileLocked(File file) {
        var f = new File(file.getAbsolutePath() + ".lock");
        if (f.exists())
            return true;
        return false;
    }

    public static boolean isFileLocked(String path) {
        return isFileLocked(new File(path));
    }

    public static void removeLockedFile(File file) {
        if (isFileLocked(file))
            file = new File(file.getAbsolutePath() + ".lock");
        file.delete();
    }

    public static void removeLockedFile(String path) {
        removeLockedFile(new File(path));
    }

    public static boolean downloadFile(File file, URL url, Map<String, String> headers) {
        var client = new OkHttpClient();
        var downloadRequestBuilder = new Request.Builder().url(url);

        if (headers != null)
            for (var i : headers.keySet()) {
                downloadRequestBuilder.addHeader(i, headers.get(i));
            }

        var downloadRequest = downloadRequestBuilder.build();
        var call = client.newCall(downloadRequest);

        try {
            var r = call.execute();
            writeFile(file, r.body().charStream());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    private static void writeFile(File path, Reader reader) throws IOException {
        var filePath = path.getPath();

        // Is locked
        if (isFileLocked(path))
            return;
        else
            path = new File(filePath + ".lock");

        // Buffer
        var len = 0;
        var buffer = new char[2048];

        // Buffered writer
        var fileBufferWriter = new BufferedWriter(new FileWriter(path));

        while ((len = reader.read(buffer)) != -1) {
            fileBufferWriter.write(buffer, 0, len);
        }

        fileBufferWriter.close();

        // Unlock
        path.renameTo(new File(filePath.replaceAll(".lock", "")));

    }
}
