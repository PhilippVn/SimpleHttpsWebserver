package https.request;

import https.method.Method;

import java.io.File;

public interface RequestHeaderInterface {
    /**
     * Attempts to parse the recieved Header
     * @param in : Input
     * @return RequestHeaderInterface
     */
    RequestHeaderInterface parseRequest(String in);

    Method getMethod();

    File getRequestedFile();

    String getUrl();

    String getHttpsVersion();

    String getClient();

}
