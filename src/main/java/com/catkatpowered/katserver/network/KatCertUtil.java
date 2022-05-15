package com.catkatpowered.katserver.network;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.common.constants.KatConfigNodeConstants;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.eclipse.jetty.util.ssl.SslContextFactory;

import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.util.Calendar;
import java.util.Random;

public class KatCertUtil {

    public static SslContextFactory getSslContextFactory() {
        if (KatServer.KatConfigAPI
                .<Boolean>getConfig(KatConfigNodeConstants.KAT_CONFIG_NETWORK_CUSTOM_CERT_ENABLED).get()) {
            // 自定义证书
            SslContextFactory sslContextFactory = new SslContextFactory.Server();
            sslContextFactory.setKeyStorePath(KatServer.KatConfigAPI
                    .<String>getConfig(KatConfigNodeConstants.KAT_CONFIG_NETWORK_CUSTOM_CERT_PATH).get());
            sslContextFactory.setKeyStorePassword(KatServer.KatConfigAPI
                    .<String>getConfig(KatConfigNodeConstants.KAT_CONFIG_NETWORK_CUSTOM_CERT_PASSWORD).get());
            return sslContextFactory;
        } else {
            // 生成一次性自签证书
            SslContextFactory sslContextFactory = new SslContextFactory.Server();
            KeyStore keyStore = getKeyStore();
            sslContextFactory.setKeyStore(keyStore);
            sslContextFactory.setKeyStorePassword("catmoe");
            return sslContextFactory;
        }
    }

    @SuppressWarnings("SpellCheckingInspection")
    private static KeyStore getKeyStore() {
        String password = KatServer.KatConfigAPI
                .<String>getConfig(KatConfigNodeConstants.KAT_CONFIG_NETWORK_SELFGEN_CERT_PASSWORD).get();
        String alias = KatServer.KatConfigAPI
                .<String>getConfig(KatConfigNodeConstants.KAT_CONFIG_NETWORK_SELFGEN_CERT_ALIAS).get();
        Security.addProvider(new BouncyCastleProvider());
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", BouncyCastleProvider.PROVIDER_NAME);
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            Certificate CAcertificate = new JcaX509CertificateConverter().setProvider(BouncyCastleProvider.PROVIDER_NAME).getCertificate(createCertificate("CN=CA", "CN=CA", publicKey, privateKey));
            Certificate CLcertificate = new JcaX509CertificateConverter().setProvider(BouncyCastleProvider.PROVIDER_NAME).getCertificate(createCertificate("CN=CA", "CN=CA", publicKey, privateKey));

            java.security.cert.Certificate[] outChain = {
                    CLcertificate, CAcertificate};
            KeyStore outStore = KeyStore.getInstance("PKCS12");
            outStore.load(null, password.toCharArray());
            outStore.setKeyEntry(alias, privateKey, password.toCharArray(),
                    outChain);
            return outStore;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static X509CertificateHolder createCertificate(String dn, String issuer,
                                                           PublicKey publicKey, PrivateKey privateKey) throws Exception {
        X509v3CertificateBuilder certificateBuilder = new X509v3CertificateBuilder(
                new X500Name(dn),
                BigInteger.valueOf(Math.abs(new Random().nextLong())),
                Calendar.getInstance().getTime(),
                Calendar.getInstance().getTime(),
                new X500Name(dn),
                SubjectPublicKeyInfo.getInstance(
                        ASN1Sequence.getInstance(publicKey.getEncoded()))
        );

        ContentSigner contentSigner = new JcaContentSignerBuilder("SHA256WithRSA").setProvider("BC").build(privateKey);
        return certificateBuilder.build(contentSigner);
    }
}