package main;

import https.certificate.Certificate;
import https.server.HTTPSServer;

/**
 * Main Class: Starts HTTPS Server
 */
public class Main {

    public static void main(String[] args) {
        Certificate certificate = new Certificate("keystore.jks","sackgesicht".toCharArray());
        HTTPSServer httpsServer = new HTTPSServer(certificate,8080,"TLSv1.2");
        httpsServer.start();
    }
}
