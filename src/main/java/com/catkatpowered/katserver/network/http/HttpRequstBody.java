package com.catkatpowered.katserver.network.http;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class HttpRequstBody {
    @SerializedName("token")
    private String token;

    @SerializedName("resource_hash")
    private String resourceHash;
}
