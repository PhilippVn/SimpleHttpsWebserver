package https.response;

import https.MIMEType.MIMEType;
import https.code.Code;
import https.request.RequestHeaderInterface;

import java.util.Date;

public interface ResponseHeaderInterface {
    ResponseHeaderInterface parseHTTPResponse(String in);

    Code getCode();

    String getMessage();

    boolean getKeepAlive();

    MIMEType getMIMEType();

    String getCharset();

    int getContentLength();

    Date getDate();

    String getHostName();
}
