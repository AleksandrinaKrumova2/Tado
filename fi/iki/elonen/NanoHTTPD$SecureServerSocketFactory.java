package fi.iki.elonen;

import java.io.IOException;
import java.net.ServerSocket;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

public class NanoHTTPD$SecureServerSocketFactory implements NanoHTTPD$ServerSocketFactory {
    private String[] sslProtocols;
    private SSLServerSocketFactory sslServerSocketFactory;

    public NanoHTTPD$SecureServerSocketFactory(SSLServerSocketFactory sslServerSocketFactory, String[] sslProtocols) {
        this.sslServerSocketFactory = sslServerSocketFactory;
        this.sslProtocols = sslProtocols;
    }

    public ServerSocket create() throws IOException {
        SSLServerSocket ss = (SSLServerSocket) this.sslServerSocketFactory.createServerSocket();
        if (this.sslProtocols != null) {
            ss.setEnabledProtocols(this.sslProtocols);
        } else {
            ss.setEnabledProtocols(ss.getSupportedProtocols());
        }
        ss.setUseClientMode(false);
        ss.setWantClientAuth(false);
        ss.setNeedClientAuth(false);
        return ss;
    }
}
