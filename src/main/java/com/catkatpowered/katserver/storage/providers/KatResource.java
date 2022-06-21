package com.catkatpowered.katserver.storage.providers;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;

import java.net.URI;
import java.util.Objects;

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

    public boolean equalHash(String hashString) {
        return Objects.equals(hashString, this.hash);
    }

    public boolean isHashed() {
        return this.hash.length() == 0;
    }

    public boolean isUnknownSize() {
        return this.size == -1;
    }

}