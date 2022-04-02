package com.catkatpowered.katserver.network;

import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.util.Calendar;
import java.util.Random;

public class KatCertUtil {

    @SuppressWarnings("SpellCheckingInspection")
    public static KeyStore getKeyStore() {
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
            outStore.load(null, "catmoe".toCharArray());
            outStore.setKeyEntry("catmoe", privateKey, "catmoe".toCharArray(),
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