package fi.iki.elonen;

import java.io.OutputStream;

public interface NanoHTTPD$TempFile {
    void delete() throws Exception;

    String getName();

    OutputStream open() throws Exception;
}
