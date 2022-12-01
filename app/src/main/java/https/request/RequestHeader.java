package https.request;

import https.MIMEType.MIMEType;
import https.method.Method;

import java.io.File;
import java.util.List;

public class RequestHeader implements RequestHeaderInterface{
    @Override
    public RequestHeaderInterface parseHTTPRequest(String in) {
        return null;
    }

    @Override
    public Method getMethod() {
        return null;
    }

    @Override
    public File getURI() {
        return null;
    }

    @Override
    public String getHttpsVersion() {
        return null;
    }

    @Override
    public boolean getKeepAlive() {
        return false;
    }

    @Override
    public List<MIMEType> getAcceptedMimeType() {
        return null;
    }
}
