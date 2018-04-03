package fi.iki.elonen;

import fi.iki.elonen.NanoHTTPD.HTTPSession;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.logging.Level;

public class NanoHTTPD$ClientHandler implements Runnable {
    private final Socket acceptSocket;
    private final InputStream inputStream;
    final /* synthetic */ NanoHTTPD this$0;

    public NanoHTTPD$ClientHandler(NanoHTTPD nanoHTTPD, InputStream inputStream, Socket acceptSocket) {
        this.this$0 = nanoHTTPD;
        this.inputStream = inputStream;
        this.acceptSocket = acceptSocket;
    }

    public void close() {
        NanoHTTPD.access$000(this.inputStream);
        NanoHTTPD.access$000(this.acceptSocket);
    }

    public void run() {
        try {
            OutputStream outputStream = this.acceptSocket.getOutputStream();
            HTTPSession session = new HTTPSession(this.this$0, NanoHTTPD.access$100(this.this$0).create(), this.inputStream, outputStream, this.acceptSocket.getInetAddress());
            while (!this.acceptSocket.isClosed()) {
                session.execute();
            }
            NanoHTTPD.access$000(outputStream);
            NanoHTTPD.access$000(this.inputStream);
            NanoHTTPD.access$000(this.acceptSocket);
            this.this$0.asyncRunner.closed(this);
        } catch (Exception e) {
            if (!(((e instanceof SocketException) && "NanoHttpd Shutdown".equals(e.getMessage())) || (e instanceof SocketTimeoutException))) {
                NanoHTTPD.access$200().log(Level.SEVERE, "Communication with the client broken, or an bug in the handler code", e);
            }
            NanoHTTPD.access$000(null);
            NanoHTTPD.access$000(this.inputStream);
            NanoHTTPD.access$000(this.acceptSocket);
            this.this$0.asyncRunner.closed(this);
        } catch (Throwable th) {
            NanoHTTPD.access$000(null);
            NanoHTTPD.access$000(this.inputStream);
            NanoHTTPD.access$000(this.acceptSocket);
            this.this$0.asyncRunner.closed(this);
        }
    }
}
