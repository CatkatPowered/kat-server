package com.catkatpowered.katserver.storage.providers;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

@Data
@Builder
public class KatResource {
    public String URL;
    public String Hash;
    /**
     * 为 -1 时，长度未知
     */
    @Default
    public Integer Size = -1;
}