package com.catkatpowered.katserver.network.websocket.packet;

import com.catkatpowered.katserver.common.constants.KatPacketTypeConstants;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

/**
 * 用于描述服务器的信息
 *
 * @author hanbings
 */
@Data
@Builder
public class ServerDescriptionPacket {

  // 数据包类型
  @SerializedName("type")
  private final String type = KatPacketTypeConstants.SERVER_DESCRIPTION_PACKET;

  // 服务器名称
  @SerializedName("server")
  String server;

  // 服务器版本
  @SerializedName("version")
  String version;

  // 服务器描述
  @SerializedName("description")
  String description;

  // 所有接入的IM桥及其描述
  // TODO: 改为自动获取,等待imbridge接入系统完工并提供API
  @SerializedName("im_bridge_description")
  String imBridgeDescription;

  public ServerDescriptionPacket(
    String server,
    String version,
    String imBridgeDescription,
    String description
  ) {
    this.server = server;
    this.version = version;
    this.imBridgeDescription = imBridgeDescription;
    this.description = description;
  }
}
