package fi.iki.elonen;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;

public class NanoHTTPD$ServerRunnable implements Runnable {
    private IOException bindException;
    private boolean hasBinded = false;
    final /* synthetic */ NanoHTTPD this$0;
    private final int timeout;

    public NanoHTTPD$ServerRunnable(NanoHTTPD nanoHTTPD, int timeout) {
        this.this$0 = nanoHTTPD;
        this.timeout = timeout;
    }

    public void run() {
        try {
            NanoHTTPD.access$900(this.this$0).bind(NanoHTTPD.access$700(this.this$0) != null ? new InetSocketAddress(NanoHTTPD.access$700(this.this$0), NanoHTTPD.access$800(this.this$0)) : new InetSocketAddress(NanoHTTPD.access$800(this.this$0)));
            this.hasBinded = true;
            do {
                try {
                    Socket finalAccept = NanoHTTPD.access$900(this.this$0).accept();
                    if (this.timeout > 0) {
                        finalAccept.setSoTimeout(this.timeout);
                    }
                    this.this$0.asyncRunner.exec(this.this$0.createClientHandler(finalAccept, finalAccept.getInputStream()));
                } catch (IOException e) {
                    NanoHTTPD.access$200().log(Level.FINE, "Communication with the client broken", e);
                }
            } while (!NanoHTTPD.access$900(this.this$0).isClosed());
        } catch (IOException e2) {
            this.bindException = e2;
        }
    }
}
