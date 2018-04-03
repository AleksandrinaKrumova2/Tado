package com.tado.android.demo;

import com.squareup.duktape.Duktape;
import com.tado.android.app.TadoApplication;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.Util;
import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD$Response$Status;
import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DemoServer extends NanoHTTPD {
    private static final String BASE_PATH = "demo/MobileServer/build/MobileServer.jsbundle/";
    private static final String DIR_MODULES = "modules";
    private static final String FILE_CONTENTS = "Contents.json";
    private static final String HEADER_CONTENT_LENGTH = "content-length";
    private Duktape duktape = Duktape.create();
    JsDemoServerCallback jsDemoServerCallback = new C07711();
    private HashMap<String, JsDemoServerResponse> responses = new HashMap();

    class C07711 implements JsDemoServerCallback {
        C07711() {
        }

        public void respond(int status, String body, String context) {
            DemoServer.this.responses.put(context, new JsDemoServerResponse(status, body));
            Snitcher.start().log(6, "ds", "status " + status, new Object[0]);
        }
    }

    public DemoServer(int port) {
        super(port);
        this.duktape.evaluate("this.nativeHost = this;");
        if (loadModules()) {
            this.duktape.evaluate(String.format("var httpServer = new DemoServer({locale: '%s'});", new Object[]{Util.getSupportedLanguage()}));
            this.duktape.set("JsDemoServerCallback", JsDemoServerCallback.class, this.jsDemoServerCallback);
            return;
        }
        this.duktape.close();
        Snitcher.start().toCrashlytics().log(6, "DemoServer", "Error loading the required modules", new Object[0]);
    }

    public Response serve(IHTTPSession session) {
        String context = UUID.randomUUID().toString();
        try {
            StringBuilder js = new StringBuilder();
            js.append("request = {};");
            js.append(String.format("request.path = '%s';", new Object[]{session.getUri()}));
            js.append(String.format("request.method = '%s';", new Object[]{session.getMethod().toString()}));
            js.append(String.format("request.query = %s;", new Object[]{convertQueryStringToJson(session.getQueryParameterString())}));
            if (session.getHeaders().containsKey(HEADER_CONTENT_LENGTH)) {
                int contentLength = Integer.parseInt((String) session.getHeaders().get(HEADER_CONTENT_LENGTH));
                session.getInputStream().read(new byte[contentLength], 0, contentLength);
                js.append(String.format("request.body = '%s';", new Object[]{new String(buffer, HttpRequest.CHARSET_UTF8)}));
            } else {
                js.append(String.format("request.body = null;", new Object[0]));
            }
            js.append("httpServer.handleRequest(request, function(response, context) { JsDemoServerCallback.respond(response.status, response.body, context); },'" + context + "');");
            this.duktape.evaluate(js.toString());
            JsDemoServerResponse response = getResponseForContext(context);
            return NanoHTTPD.newFixedLengthResponse(NanoHTTPD$Response$Status.lookup(response.getStatus()), NanoHTTPD.MIME_HTML, response.getBody());
        } catch (Exception e) {
            Snitcher.start().logException(e);
            return NanoHTTPD.newFixedLengthResponse(e.getMessage());
        }
    }

    private boolean loadModules() {
        Exception e;
        try {
            for (String module : getListOfModules()) {
                this.duktape.evaluate(getFileContents(module + ".js"));
            }
            return true;
        } catch (IOException e2) {
            e = e2;
            Snitcher.start().logException(e);
            return false;
        } catch (JSONException e3) {
            e = e3;
            Snitcher.start().logException(e);
            return false;
        }
    }

    private String[] getListOfModules() throws IOException, JSONException {
        JSONArray modulesList = new JSONObject(getFileContents(FILE_CONTENTS)).getJSONArray(DIR_MODULES);
        String[] moduleArray = new String[modulesList.length()];
        for (int i = 0; i < modulesList.length(); i++) {
            moduleArray[i] = modulesList.getString(i);
        }
        return moduleArray;
    }

    private String getFileContents(String filename) throws IOException {
        InputStream assetsInputStream = TadoApplication.getTadoAppContext().getAssets().open(BASE_PATH + filename);
        byte[] contentsBuffer = new byte[assetsInputStream.available()];
        assetsInputStream.read(contentsBuffer);
        assetsInputStream.close();
        return new String(contentsBuffer, HttpRequest.CHARSET_UTF8);
    }

    private JsDemoServerResponse getResponseForContext(String context) {
        JsDemoServerResponse response = (JsDemoServerResponse) this.responses.get(context);
        this.responses.remove(context);
        return response;
    }

    public void stop() {
        this.duktape.close();
        super.stop();
    }

    private String convertQueryStringToJson(String queryString) {
        return "{\"" + queryString.replaceAll("=", "\":\"").replaceAll("&", "\",\"") + "\"}";
    }
}
