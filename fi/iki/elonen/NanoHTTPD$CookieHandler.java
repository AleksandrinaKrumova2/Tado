package fi.iki.elonen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NanoHTTPD$CookieHandler implements Iterable<String> {
    private final HashMap<String, String> cookies = new HashMap();
    private final ArrayList<NanoHTTPD$Cookie> queue = new ArrayList();
    final /* synthetic */ NanoHTTPD this$0;

    public NanoHTTPD$CookieHandler(NanoHTTPD nanoHTTPD, Map<String, String> httpHeaders) {
        this.this$0 = nanoHTTPD;
        String raw = (String) httpHeaders.get("cookie");
        if (raw != null) {
            for (String token : raw.split(";")) {
                String[] data = token.trim().split("=");
                if (data.length == 2) {
                    this.cookies.put(data[0], data[1]);
                }
            }
        }
    }

    public void delete(String name) {
        set(name, "-delete-", -30);
    }

    public Iterator<String> iterator() {
        return this.cookies.keySet().iterator();
    }

    public String read(String name) {
        return (String) this.cookies.get(name);
    }

    public void set(NanoHTTPD$Cookie cookie) {
        this.queue.add(cookie);
    }

    public void set(String name, String value, int expires) {
        this.queue.add(new NanoHTTPD$Cookie(name, value, NanoHTTPD$Cookie.getHTTPTime(expires)));
    }

    public void unloadQueue(NanoHTTPD$Response response) {
        Iterator i$ = this.queue.iterator();
        while (i$.hasNext()) {
            response.addHeader("Set-Cookie", ((NanoHTTPD$Cookie) i$.next()).getHTTPHeader());
        }
    }
}
