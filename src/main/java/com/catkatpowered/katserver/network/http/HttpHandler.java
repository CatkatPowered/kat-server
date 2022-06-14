package com.catkatpowered.katserver.network.http;

import com.catkatpowered.katserver.KatServer;
import com.google.gson.Gson;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

// 此处处理用HTTP协议传输的资源文件
// TODO: 此处依赖文件储存系统
public class HttpHandler implements Handler {
    Gson gson = new Gson();
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        HttpRequstBody body = gson.fromJson(new String(ctx.body().getBytes()), HttpRequstBody.class);
        if (KatServer.KatTokenPoolAPI.checkToken(body.getToken())) {
            // 等待文件储存系统完成后根据hash获取文件
        }
    }
}
