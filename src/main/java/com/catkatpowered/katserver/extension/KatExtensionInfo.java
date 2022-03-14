package com.catkatpowered.katserver.extension;

import lombok.Data;

import java.util.List;

/**
 * 实体类 存放扩展描述文件
 */
@Data
public class KatExtensionInfo {
    // 扩展主函数
    public String main;
    // 扩展名
    public String extension;
    // 扩展版本
    public String version;
    // 扩展网站
    public String website;
    // 扩展作者
    public List<String> author;
    // 扩展依赖
    public List<String> depend;
}
