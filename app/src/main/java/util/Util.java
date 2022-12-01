package util;


import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.*;
import java.nio.file.Paths;
import java.security.KeyStore;

public class Util {
    public static File getRoot(){
        return new File(Paths.get("","src","main","resources").toAbsolutePath().toString());
    }

    public static File getCertificateRoot(){
        return new File(getRoot(),"certificate");
    }

    public static File getWebRoot(){
        return new File(getRoot(),"html");
    }

    /**
     * Read File contents into a Buffer (byte array)
     *
     * @param file       : File to read from
     * @param fileLength : Number of Bytes to read
     * @return Buffer containing File Data
     * @throws FileNotFoundException : requested file does not exists
     * @throws IOException
     */
    public static byte[] readFileData(File file, int fileLength) throws IOException {
        FileInputStream fileIn = null;
        byte[] fileData = new byte[fileLength];

        try {
            fileIn = new FileInputStream(file);
            fileIn.read(fileData);
        } finally {
            if (fileIn != null)
                fileIn.close();
        }

        return fileData;
    }

}
