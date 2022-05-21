package com.catkatpowered.katserver.network;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.KatServerMain;
import com.catkatpowered.katserver.common.constants.KatConfigNodeConstants;
import com.catkatpowered.katserver.config.KatConfig;
import com.catkatpowered.katserver.config.KatConfigManager;
import com.catkatpowered.katserver.database.KatDatabaseManager;
import com.catkatpowered.katserver.event.KatEventManager;
import com.catkatpowered.katserver.event.events.MessageReceiveEvent;
import com.catkatpowered.katserver.event.events.MessageSendEvent;
import com.catkatpowered.katserver.event.interfaces.EventHandler;
import com.catkatpowered.katserver.event.interfaces.Listener;
import com.catkatpowered.katserver.extension.KatExtensionManager;
import com.catkatpowered.katserver.message.KatUniMessage;
import com.catkatpowered.katserver.storage.KatStorageManager;
import com.catkatpowered.katserver.task.KatTaskManager;
import com.google.gson.Gson;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import javax.net.ssl.*;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestKatNetwork {
    private static OkHttpClient wsClient;
    private static Boolean connected = null;
    private static final KatUniMessage plainTextMessage = KatUniMessage.builder()
            .extensionID("testExtension")
            .messageType("PlainMessage")
            .messageGroup("101010101")
            .messageID("uuid")
            .messageContent("欸嘿~")
            .messageTimeStamp(1652881882L)
            .build();
    @SuppressWarnings("SpellCheckingInspection")
    private static final String defaultConfig = """
            ######################### Network #############################
            network:
              network_port: 25565
              selfgen_cert:
                cert_password: "catmoe"
                cert_alias: "catmoe"
              custom_cert:
                enabled: false
                cert_path: "./cert.jks"
                cert_password: "catmoe"
                        
                        
            ######################### Database ############################
            database:
              # default support mongodb
              # demo database_url:
              # mongodb: mongodb://localhost:27017/database_name
              #
              # Note: No need to carry username and password in url
              database_url: mongodb://localhost:27017/kat-server
              database_username:
              database_password:
                        
            ####################### Storage ###############################
            # The resource file storage
            resource:
              # You can choose those type of resource_storage
              # 1.local
              resource_storage: local
                        
              # If you choose `local`,
              # you can set the resource file where to store.
              data_folder_path: "./data"
                        
            ####################### ExecThreads ############################
            exec:
              exec_threads: 16
                        
            ####################### TokenPool ##############################
            # This section are the settings of token pool
                        
            tokenpool:
              # You can set the token how long to live
              #
              # Milisecond
              outdate: 10000
            """;

    @BeforeAll
    public static void start() {
        // 启动配置文件模块
        Map<String, Object> yml = new Yaml().load(defaultConfig);
        KatConfig.getInstance().setConfigContent(yml);
        // 启动事件总线模块
        KatEventManager.init();
        // 启动网络模块
        KatNetworkManager.init();
        // 启动数据库
        KatDatabaseManager.init();
        // 启动储存模块
        KatStorageManager.init();
        // 启动多线程模块
        KatTaskManager.init();
        // 启动扩展模块
        KatExtensionManager.init();

        KeyStore keyStore = KatCertUtil.getKeyStore();
        SSLContext sslContext = null;

        X509TrustManager trustManager = null;
        try {
            trustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            sslContext = SSLContext.getInstance("SSL");
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, "catmoe".toCharArray());
            sslContext.init(keyManagerFactory.getKeyManagers(), new TrustManager[]{trustManager}, new SecureRandom());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 初始化客户端
        wsClient = new OkHttpClient.Builder()
                .readTimeout(0, TimeUnit.MILLISECONDS)
                .sslSocketFactory(sslContext.getSocketFactory(), trustManager)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .build();
    }

    @Test
    public void connection() throws InterruptedException {
        Request request = new Request.Builder()
                .url("wss://localhost:" + KatServer.KatConfigAPI
                        .<Integer>getConfig(KatConfigNodeConstants.KAT_CONFIG_NETWORK_PORT).get() + "/websocket")
                .build();
        class Listener extends WebSocketListener {
            @Override
            public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
                connected = true;
            }

            @Override
            public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                connected = false;
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                connected = false;
            }
        }
        wsClient.newWebSocket(request, new Listener());
        while (true) {
            Thread.currentThread().join(1000);
            if (connected != null)
                break;
        }
        assertTrue(connected);
        connected = null;
    }

    @Test
    public void newMessageBroadcast() throws InterruptedException {
        connected = false;
        Request request = new Request.Builder()
                .url("wss://localhost:" + KatServer.KatConfigAPI
                        .<Integer>getConfig(KatConfigNodeConstants.KAT_CONFIG_NETWORK_PORT).get() + "/websocket")
                .build();
        class Listener extends WebSocketListener {
            @Override
            public void onMessage(@NotNull WebSocket webSocket, String text) {
                if (text.contains("server_description")) {
                    connected= true;
                    return;
                }
                //{"message":{"message_type":"testExtension","extension_id":"PlainMessage","message_group":"101010101","message_id":"uuid","message_content":"欸嘿~","message_timestamp":1652881882},"type":"websocket_message"}
                assertEquals(text, "{\"message\":{\"message_type\":\"testExtension\",\"extension_id\":\"PlainMessage\",\"message_group\":\"101010101\",\"message_id\":\"uuid\",\"message_content\":\"欸嘿~\",\"message_timestamp\":1652881882},\"type\":\"websocket_message\"}");
                connected = null;
            }
        }

        wsClient.newWebSocket(request, new Listener());
        while (true) {
            Thread.currentThread().join(2000);
            if (connected)
                break;
        }
        KatEventManager.callEvent(new MessageReceiveEvent(plainTextMessage));
    }

    @Test
    public void newMessageIncome() {
        Request request = new Request.Builder()
                .url("wss://localhost:" + KatServer.KatConfigAPI
                        .<Integer>getConfig(KatConfigNodeConstants.KAT_CONFIG_NETWORK_PORT).get() + "/websocket")
                .build();
        WebSocket webSocket = wsClient.newWebSocket(request, new WebSocketListener() {});
        Gson gson = new Gson();
        class eventListener implements Listener{
            @EventHandler
            public void onMessage(MessageSendEvent event) {
                assertEquals(gson.toJson(event.getMessage()), "{\"message\":{\"message_type\":\"testExtension\",\"extension_id\":\"PlainMessage\",\"message_group\":\"101010101\",\"message_id\":\"uuid\",\"message_content\":\"欸嘿~\",\"message_timestamp\":1652881882},\"type\":\"websocket_message\"}");
            }
        }
        KatServer.KatEventBusAPI.registerListener(new eventListener());
        webSocket.send("{\"message\":{\"message_type\":\"testExtension\",\"extension_id\":\"PlainMessage\",\"message_group\":\"101010101\",\"message_id\":\"uuid\",\"message_content\":\"欸嘿~\",\"message_timestamp\":1652881882},\"type\":\"websocket_message\"}");
    }
}