package com.catkatpowered.katserver.network.http;

import com.catkatpowered.katserver.KatServer;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.UploadedFile;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;

@Slf4j
public class HttpPostHandler implements Handler {
    Gson gson = new Gson();

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String resourceHash = ctx.pathParam("resourceHash");
        String resourceName = ctx.pathParam("resourceName");
        UploadedFile uploadedFile = ctx.uploadedFile(resourceName);
        InputStream inputStream = uploadedFile.content();
        KatServer.KatStorageAPI.upload(resourceHash, inputStream);
        // 返回对应的token
        ctx.json(gson.toJson(HttpPostResponse.builder().resourceToken(KatServer.KatTokenPoolAPI.newToken()).build()));
        throw new BadRequestResponse();

    }
}

@Builder
class HttpPostResponse {
    @SerializedName("resource_token")
    String resourceToken;
}
