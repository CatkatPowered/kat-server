package com.catkatpowered.katserver.network;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.KatServerMain;
import com.catkatpowered.katserver.common.constants.KatConfigNodeConstants;
import com.catkatpowered.katserver.event.KatEventManager;
import com.catkatpowered.katserver.event.events.MessageReceiveEvent;
import com.catkatpowered.katserver.event.events.MessageSendEvent;
import com.catkatpowered.katserver.event.interfaces.EventHandler;
import com.catkatpowered.katserver.event.interfaces.Listener;
import com.catkatpowered.katserver.message.KatUniMessage;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

    @Test
    public void network() throws InterruptedException {
        TestKatNetwork test = new TestKatNetwork();
        ExecutorService executor = Executors.newFixedThreadPool(10);

        List<Runnable> runnable = new ArrayList<>() {{
            add(test::server);
            add(() -> {
                try {
                    test.connection();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            add(() -> {
                try {
                    test.newMessageBroadcast();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            add(test::newMessageIncome);
        }};

        runnable.forEach(r -> {
            executor.submit(r);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void server() {
        KatServerMain.main(new String[0]);
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
            public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, Response response) {
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

    public void newMessageBroadcast() throws InterruptedException {
        connected = false;
        Request request = new Request.Builder()
                .url("wss://localhost:" + KatServer.KatConfigAPI
                        .<Integer>getConfig(KatConfigNodeConstants.KAT_CONFIG_NETWORK_PORT).get() + "/websocket")
                .build();
        class Listener extends WebSocketListener {
            @Override
            public void onMessage(WebSocket webSocket, String text) {
                if (text.contains("server_description")) {
                    connected = true;
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

    public void newMessageIncome() {
        Request request = new Request.Builder()
                .url("wss://localhost:" + KatServer.KatConfigAPI
                        .<Integer>getConfig(KatConfigNodeConstants.KAT_CONFIG_NETWORK_PORT).get() + "/websocket")
                .build();
        WebSocket webSocket = wsClient.newWebSocket(request, new WebSocketListener() {
        });
        Gson gson = new Gson();
        class eventListener implements Listener {
            @EventHandler
            public void onMessage(MessageSendEvent event) {
                assertEquals(gson.toJson(event.getMessage()), "{\"message\":{\"message_type\":\"testExtension\",\"extension_id\":\"PlainMessage\",\"message_group\":\"101010101\",\"message_id\":\"uuid\",\"message_content\":\"欸嘿~\",\"message_timestamp\":1652881882},\"type\":\"websocket_message\"}");
            }
        }
        KatServer.KatEventBusAPI.registerListener(new eventListener());
        webSocket.send("{\"message\":{\"message_type\":\"testExtension\",\"extension_id\":\"PlainMessage\",\"message_group\":\"101010101\",\"message_id\":\"uuid\",\"message_content\":\"欸嘿~\",\"message_timestamp\":1652881882},\"type\":\"websocket_message\"}");
    }
}
