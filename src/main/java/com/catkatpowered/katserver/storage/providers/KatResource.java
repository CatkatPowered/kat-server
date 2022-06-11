package com.catkatpowered.katserver.storage.providers;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;

import java.net.URI;


@Builder
public class KatResource {
    @Getter
    private URI uri;
    @Default
    @Getter
    private String hash = "";

    /**
     * 为 -1 时，长度未知
     */
    @Default
    @Getter
    private Long size = (long) -1;

    public boolean isHashed() {
        return this.hash.length() == 0;
    }

    public boolean unknownSize() {
        return this.size == -1;
    }

}