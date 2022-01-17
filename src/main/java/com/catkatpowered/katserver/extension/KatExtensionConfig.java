package com.catkatpowered.katserver.extension;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 实体类 存放扩展配置文件
 */
public class KatExtensionConfig {
    // 扩展名
    @Setter @Getter @SerializedName("extension")
    public String extension;
    // 扩展版本
    @Setter @Getter @SerializedName("version")
    public String version;
    // 扩展网站
    @Setter @Getter @SerializedName("website")
    public String website;
    // 扩展作者
    @Setter @Getter @SerializedName("author")
    public List<String> author;
    // 扩展依赖
    @Setter @Getter @SerializedName("depend")
    public List<String> depend;
}
