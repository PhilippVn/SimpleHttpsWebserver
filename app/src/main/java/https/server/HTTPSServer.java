package https.server;

import https.certificate.Certificate;

import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.PrintWriter;
import java.net.Socket;

public class HTTPSServer {
    private final Certificate certificate;
    private final int port;
    private final String tlsVersion;

    public HTTPSServer(Certificate certificate, int port, String tlsVersion) {
        this.certificate = certificate;
        if(port <= 0){
            throw new IllegalArgumentException("Port number must be positive");
        }
        this.port = port;
        this.tlsVersion = tlsVersion;
    }

    public void start(){
        SSLServerSocketFactory ssf = null; // stupid compiler
        try {
            ssf = certificate.init().getServerSocketFactory();
        }catch (Exception e){
            System.err.println("Critical: Server start failed:\n" + e.getLocalizedMessage());
            e.printStackTrace();
            System.exit(1);
        }

        try(SSLServerSocket server = (SSLServerSocket) ssf.createServerSocket(this.port)){
            server.setNeedClientAuth(false);
            server.setEnabledProtocols(new String[]{this.tlsVersion});
            System.out.println("Server started. Waiting for connections...");
            while(true){
                try{
                    new Thread(new HTTPServerThread(server.accept())).start();
                }catch (Exception e){
                    System.err.println("Client Connection Failed:");
                    System.err.println(e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }

        }catch (Exception e){
            System.err.println("Critical: Server start failed:\n" + e.getLocalizedMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
