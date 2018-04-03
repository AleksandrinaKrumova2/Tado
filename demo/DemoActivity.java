package com.tado.android.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.utils.Snitcher;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DemoActivity extends AppCompatActivity {
    private static final String PATH = "debug/echo";
    private static final int PORT = 8080;
    private OkHttpClient client;
    private TextView ip;
    private DemoServer server;

    class C07701 implements Callback {
        C07701() {
        }

        public void onResponse(Call call, Response response) throws IOException {
            Snitcher.start().log(4, "webserver", response.body().string(), new Object[0]);
        }

        public void onFailure(Call call, IOException e) {
            Snitcher.start().log(4, "webserver", "KO " + e.getMessage(), new Object[0]);
        }
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.demo);
        this.ip = (TextView) findViewById(C0676R.id.ip);
        this.server = new DemoServer(PORT);
        this.client = new Builder().connectTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();
    }

    protected void onStart() {
        super.onStart();
        try {
            this.server.start();
            this.ip.setText(String.format("http://localhost:%d", new Object[]{Integer.valueOf(this.server.getListeningPort())}));
            this.ip.setMovementMethod(new LinkMovementMethod());
            doPost(PATH);
        } catch (IOException e) {
            Snitcher.start().toCrashlytics().logException("webserver", e);
        }
    }

    protected void onStop() {
        try {
            this.server.stop();
        } catch (Exception e) {
            Snitcher.start().toCrashlytics().logException("webserver", e);
        }
        super.onStop();
    }

    private void doPost(String path) {
        Snitcher.start().log(4, "webserver", "posting to %s", String.format(Locale.getDefault(), "http://%s:%d/%s", new Object[]{"localhost", Integer.valueOf(PORT), path}));
        this.client.newCall(new Request.Builder().url(url).post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "{\"value\":\"hello\"}")).build()).enqueue(new C07701());
    }
}
