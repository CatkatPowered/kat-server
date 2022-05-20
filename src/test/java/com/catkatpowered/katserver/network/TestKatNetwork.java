package com.catkatpowered.katserver.network;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.KatServerMain;
import com.catkatpowered.katserver.common.constants.KatConfigNodeConstants;
import com.catkatpowered.katserver.event.KatEventManager;
import com.catkatpowered.katserver.event.events.MessageReceiveEvent;
import com.catkatpowered.katserver.message.KatUniMessage;
import okhttp3.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.net.ssl.*;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestKatNetwork {
    private static OkHttpClient wsClient;

    @BeforeAll
    public static void start() {
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

    @Test
    public void connection() throws InterruptedException {
        final Boolean[] isSocketConnected = new Boolean[1];
        Request request = new Request.Builder()
                .url("wss://localhost:" + KatServer.KatConfigAPI
                        .<Integer>getConfig(KatConfigNodeConstants.KAT_CONFIG_NETWORK_PORT).get()+"/websocket")
                .build();
        class Listener extends WebSocketListener {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                isSocketConnected[0] = true;
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                isSocketConnected[0] = false;
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                System.out.println(t.getMessage());
                isSocketConnected[0] = false;
            }
        }
        wsClient.newWebSocket(request, new Listener());
        Thread.currentThread().join(3000);
        assertTrue(isSocketConnected[0]);
    }

    @Test
    public void newMessageBroadcast() throws InterruptedException {
        Request request = new Request.Builder()
                .url("wss://localhost:" + KatServer.KatConfigAPI
                        .<Integer>getConfig(KatConfigNodeConstants.KAT_CONFIG_NETWORK_PORT).get()+ "/websocket")
                .build();
        class Listener extends WebSocketListener {
            @Override
            public void onMessage(WebSocket webSocket, String text) {
                System.out.println(text);
            }
        }
        wsClient.newWebSocket(request, new Listener());
        KatEventManager.callEvent(new MessageReceiveEvent(new KatUniMessage(
                "testExtension",
                "PlainMessage",
                "101010101",
                "uuid",
                "欸嘿~",
                1652881882L,
                new ArrayList<KatUniMessage>(),
                new ArrayList<String>(),
                "",
                "",
                ""
        )));
        Thread.currentThread().join(10000);
    }
}
