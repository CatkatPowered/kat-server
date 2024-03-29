package com.catkatpowered.katserver.network;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.common.constants.KatConfigNodeConstants;
import com.catkatpowered.katserver.network.http.HttpGetHandler;
import com.catkatpowered.katserver.network.http.HttpPostHandler;
import com.catkatpowered.katserver.network.utils.KatCertUtil;
import com.catkatpowered.katserver.network.websocket.KatWebSocketIncome;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.websocket.api.Session;

public class KatNetwork {

    private static final KatNetwork Instance = new KatNetwork();

    @Getter
    private static Javalin network;

    // 包含所有moseeger客户端,实现无限客户端
    @Getter
    @Setter
    private static List<Session> sessions = new ArrayList<>();

    private KatNetwork() {
        Javalin server = Javalin.create(javalinConfig -> {
            javalinConfig.jetty.server(() -> {
                Server app = new Server();
                SslContextFactory.Server sslContextFactory = KatCertUtil.getSslContextFactory();
                sslContextFactory.setSniRequired(false);

                HttpConfiguration httpsConfig = new HttpConfiguration();
                SecureRequestCustomizer secureRequestCustomizer = new SecureRequestCustomizer();
                secureRequestCustomizer.setSniHostCheck(false);
                httpsConfig.addCustomizer(secureRequestCustomizer);
                HttpConnectionFactory https = new HttpConnectionFactory(httpsConfig);
                SslConnectionFactory sslConnectionFactory = new SslConnectionFactory(sslContextFactory, https.getProtocol());

                ServerConnector sslConnector = new ServerConnector(
                        app,
                        sslConnectionFactory,
                        https
                );
                sslConnector.setPort(
                        KatServer.KatConfigAPI
                                .<Long>getConfig(KatConfigNodeConstants.KAT_CONFIG_NETWORK_PORT)
                                .get()
                                .intValue()
                );

                app.setConnectors(new Connector[]{sslConnector});
                return app;
            });
        });
        // HTTP Handlers
        // mosseger向kat-server请求文件
        server.get("/resource/{resourceHash}", new HttpGetHandler());
        // mosseger向kat-server上传文件
        server.post(
                "/resource/{resourceHash}/{resourceName}",
                new HttpPostHandler()
        );

        // WebSocket Handlers
        server.ws("/websocket", KatWebSocketIncome::KatNetworkIncomeHandler);

        network = server.start();
    }

    public static KatNetwork getInstance() {
        return Instance;
    }
}
