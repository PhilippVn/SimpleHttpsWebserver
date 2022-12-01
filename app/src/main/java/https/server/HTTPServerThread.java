package https.server;

import util.Util;

import javax.net.ssl.SSLSocket;
import java.io.*;
import java.net.Socket;
import java.util.stream.Collectors;

public class HTTPServerThread implements Runnable{

    private Socket clientConnection;

    public HTTPServerThread(Socket clientConnection) {
        this.clientConnection = clientConnection;
    }

    @Override
    public void run() {
        //System.out.println("Client connected:" + this.clientConnection.toString());

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

                String header = getHeader(in);
                System.out.println("=========Request header=========");
                System.out.println(header);
                System.out.flush();

                out.print("HTTP/1.0 200 OK\r\n");
                out.print("Content-Length: " + bytecodes.length +
                        "\r\n");
                out.print("Content-Type: text/html\r\n\r\n");
                out.flush();
                rawOut.write(bytecodes,0, (int) file.length());
                rawOut.flush();
            } catch (IOException ignore) {}
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
            e.printStackTrace();
        }

    }

    private String getHeader(BufferedReader in) throws IOException{
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while((line = in.readLine()) != null)
            stringBuilder.append(line);

        return stringBuilder.toString();
    }
}
