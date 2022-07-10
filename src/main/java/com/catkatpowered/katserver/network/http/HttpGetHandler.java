package com.catkatpowered.katserver.network.http;

import com.catkatpowered.katserver.KatServer;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

public class HttpGetHandler implements Handler {
    Gson gson = new Gson();
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        HttpRequstBody body = gson.fromJson(new String(ctx.body().getBytes()), HttpRequstBody.class);
        if (KatServer.KatTokenPoolAPI.checkToken(body.getResourceToken())) {
            // 直接用stream传递，用流发送给mosseger端
            ctx.result(KatServer.KatStorageAPI.fetch(body.getResourceHash()).get());
        }
    }
}

@Data
class HttpRequstBody {
    @SerializedName("resource_token")
    private String resourceToken;

    @SerializedName("resource_hash")
    private String resourceHash;
}