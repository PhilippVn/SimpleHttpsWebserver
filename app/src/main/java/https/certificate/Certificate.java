package https.certificate;


import util.Util;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.NoSuchFileException;
import java.security.*;
import java.security.cert.CertificateException;

public class Certificate {
    private final String keyStoreName;
    private final char[] keyStorePassword;

    public Certificate(String keyStoreName, char[] keyStorePassword) {

        this.keyStoreName = keyStoreName;
        this.keyStorePassword = keyStorePassword;
    }

    /**
     * initializes TLS SSL Context
     * @return SSLContext
     */
    public SSLContext init() throws Exception {
        //setup KeyManager
        SSLContext ctx;
        KeyManagerFactory kmf;
        KeyStore ks;

        ctx = SSLContext.getInstance("TLS");
        kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        ks = KeyStore.getInstance("JKS");

        File ksFile = new File(Util.getCertificateRoot(), this.keyStoreName);
        if (!ksFile.exists() || !ksFile.isFile() || !ksFile.canRead()) {
            throw new NoSuchFileException("Certificate init(): Couldn't load Keystore. Does the File exist and is readable?");
        }

        FileInputStream ksFileStream = new FileInputStream(ksFile);

        ks.load(ksFileStream, this.keyStorePassword);
        kmf.init(ks, this.keyStorePassword);
        ctx.init(kmf.getKeyManagers(), null, null);

        ksFileStream.close();
        return ctx;
    }


}
