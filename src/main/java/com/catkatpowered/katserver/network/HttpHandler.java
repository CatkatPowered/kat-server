package com.catkatpowered.katserver.network;

import com.google.gson.Gson;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

// 此处处理用HTTP协议传输的资源文件
// TODO: 此处依赖文件储存系统
public class HttpHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Gson gson = new Gson();

    }
}
