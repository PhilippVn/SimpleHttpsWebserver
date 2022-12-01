package https.server;

import util.Util;


import java.io.*;
import java.net.Socket;

public class HTTPServerThread implements Runnable{

    private final Socket clientConnection;
    private boolean debug;

    public HTTPServerThread(Socket clientConnection) {
        this.clientConnection = clientConnection;
        this.debug = false;
    }

    public HTTPServerThread(Socket clientConnection,boolean debug) {
        this.clientConnection = clientConnection;
        this.debug = debug;
    }

    @Override
    public void run() {
        if(debug)
            System.out.println("Client connected:" + this.clientConnection.toString());

        try(BufferedReader in = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
            BufferedOutputStream rawOut = new BufferedOutputStream(clientConnection.getOutputStream());
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    rawOut)))
        ){

            File file = new File(Util.getWebRoot(), "index.html");
            byte[] bytecodes = Util.readFileData(file, (int) file.length());

            if(clientConnection.isClosed() || !clientConnection.isConnected())
                return;

            try{


                out.print("HTTP/1.0 200 OK\r\n");
                out.print("Content-Length: " + bytecodes.length +
                        "\r\n");
                out.print("Content-Type: text/html\r\n\r\n");
                out.flush();
                rawOut.write(bytecodes,0, (int) file.length());
                rawOut.flush();

                if (debug) {
                    String header = getHTTPHeader(in);
                    if(!header.isEmpty()){
                        System.out.println("=========Request header=========");
                        System.out.println(header);
                        System.out.flush();
                    }
                }

            } catch (IOException ignore) {}
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
            e.printStackTrace();
        }

    }

    public boolean getDebug(){
        return this.debug;
    }

    public void setDebug(boolean debug){
        this.debug = debug;
    }

    private String getHTTPHeader(BufferedReader in){
        try {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null)
                stringBuilder.append(line).append("\n");

            return stringBuilder.toString();
        }catch (IOException ignored){return "";}
    }
}
