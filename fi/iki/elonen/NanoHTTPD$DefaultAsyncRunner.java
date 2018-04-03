package fi.iki.elonen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class NanoHTTPD$DefaultAsyncRunner implements NanoHTTPD$AsyncRunner {
    private long requestCount;
    private final List<NanoHTTPD$ClientHandler> running = Collections.synchronizedList(new ArrayList());

    public List<NanoHTTPD$ClientHandler> getRunning() {
        return this.running;
    }

    public void closeAll() {
        Iterator i$ = new ArrayList(this.running).iterator();
        while (i$.hasNext()) {
            ((NanoHTTPD$ClientHandler) i$.next()).close();
        }
    }

    public void closed(NanoHTTPD$ClientHandler clientHandler) {
        this.running.remove(clientHandler);
    }

    public void exec(NanoHTTPD$ClientHandler clientHandler) {
        this.requestCount++;
        Thread t = new Thread(clientHandler);
        t.setDaemon(true);
        t.setName("NanoHttpd Request Processor (#" + this.requestCount + ")");
        this.running.add(clientHandler);
        t.start();
    }
}
