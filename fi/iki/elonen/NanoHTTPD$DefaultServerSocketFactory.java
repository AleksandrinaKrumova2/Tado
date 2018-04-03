package fi.iki.elonen;

import java.io.IOException;
import java.net.ServerSocket;

public class NanoHTTPD$DefaultServerSocketFactory implements NanoHTTPD$ServerSocketFactory {
    public ServerSocket create() throws IOException {
        return new ServerSocket();
    }
}
