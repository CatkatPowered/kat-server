package com.catkatpowered.katserver.message;

import lombok.Builder;
import lombok.Data;

/**
 * 用于描述服务器的信息
 *
 * @author hanbings
 */
@Data
@Builder
public class KatServerDescription {
    // 服务器名称
    String server;
    // 服务器版本
    String version;
    // 服务器描述
    String description;
}
