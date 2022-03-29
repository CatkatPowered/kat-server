package com.catkatpowered.katserver.storage;

import lombok.Builder;
import lombok.Data;
import lombok.Builder.Default;

@Data
@Builder
public class KatResource {
    public String URL;
    public String MD5;
    public String Token;
    /**
     * 
     * 为 -1 时，长度未知
     */
    @Default
    public Integer Size = -1;
}