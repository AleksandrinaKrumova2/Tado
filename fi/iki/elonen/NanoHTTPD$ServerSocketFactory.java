package fi.iki.elonen;

import java.io.IOException;
import java.net.ServerSocket;

public interface NanoHTTPD$ServerSocketFactory {
    ServerSocket create() throws IOException;
}
