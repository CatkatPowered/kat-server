package com.catkatpowered.katserver.storage.providers;

import java.net.URI;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

@Data
@Builder
public class KatResource {
    public URI uri;
    public String hash;

    /**
     * 为 -1 时，长度未知
     */
    @Default
    public Long size = (long) -1;
}