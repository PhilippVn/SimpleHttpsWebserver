package https.request;

import https.MIMEType.MIMEType;
import https.method.Method;

import java.io.File;
import java.util.List;

public interface RequestHeaderInterface {
    /**
     * Attempts to parse the recieved Header
     * @param in : Input
     * @return RequestHeaderInterface
     */
    RequestHeaderInterface parseHTTPRequest(String in);

    Method getMethod();

    File getURI();

    String getHttpsVersion();

    boolean getKeepAlive();

    List<MIMEType> getAcceptedMimeType();

}
