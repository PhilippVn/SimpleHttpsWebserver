package https.response;

import https.MIMEType.MIMEType;
import https.code.Code;

import java.util.Date;

public class ResponseHeader implements ResponseHeaderInterface{
    @Override
    public ResponseHeaderInterface parseHTTPResponse(String in) {
        return null;
    }

    @Override
    public Code getCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public boolean getKeepAlive() {
        return false;
    }

    @Override
    public MIMEType getMIMEType() {
        return null;
    }

    @Override
    public String getCharset() {
        return null;
    }

    @Override
    public int getContentLength() {
        return 0;
    }

    @Override
    public Date getDate() {
        return null;
    }

    @Override
    public String getHostName() {
        return null;
    }
}
