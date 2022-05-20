package com.catkatpowered.katserver.network;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.common.constants.KatConfigNodeConstants;
import io.javalin.Javalin;
import lombok.Getter;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.websocket.api.Session;

import java.util.ArrayList;
import java.util.List;

public class KatNetwork {
    private static final KatNetwork Instance = new KatNetwork();

    @Getter
    private static Javalin network;

    // 包含所有moseeger客户端,实现无限客户端
    @Getter
    private static List<Session> sessions = new ArrayList<Session>();

    private KatNetwork() {
        Javalin server = Javalin.create(config -> {
            config.server(() -> {
                Server app = new Server();
                SslContextFactory sslContextFactory = KatCertUtil.getSslContextFactory();
                ServerConnector sslConnector = new ServerConnector(app, sslContextFactory);
                sslConnector.setPort(KatServer.KatConfigAPI
                        .<Integer>getConfig(KatConfigNodeConstants.KAT_CONFIG_NETWORK_PORT).get());
                app.setConnectors(new Connector[]{sslConnector});
                return app;
            });
        });
        // HTTP Handlers
        server.get("/resource", new HttpHandler());

        // WebSocket Handlers
        server.ws("/websocket", KatWebSocketIncome::KatNetworkIncomeHandler);


        network = server.start();
    }

    public static KatNetwork getInstance() {
        return Instance;
    }
}
