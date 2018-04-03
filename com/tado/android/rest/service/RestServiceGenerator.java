package com.tado.android.rest.service;

import android.support.annotation.NonNull;
import com.google.gson.GsonBuilder;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import com.tado.android.demo.DemoServer;
import com.tado.android.demo.marketing.MarketingAlertsManager;
import com.tado.android.rest.deserializer.DeviceTypeDeserializer;
import com.tado.android.rest.mock.MockDataPair;
import com.tado.android.rest.mock.MockDataSource;
import com.tado.android.rest.mock.TemplateMatcher;
import com.tado.android.rest.model.AwayConfigurationAdapter;
import com.tado.android.rest.model.DeviceType;
import com.tado.android.rest.model.GenericAwayConfiguration;
import com.tado.android.rest.model.Humidity;
import com.tado.android.rest.model.InstallationClassAdapter;
import com.tado.android.rest.model.ZoneSettingClassAdapter;
import com.tado.android.rest.model.installation.GenericZoneSetting;
import com.tado.android.rest.model.installation.Installation;
import com.tado.android.utils.Constants;
import com.tado.android.utils.DateDeserializer;
import com.tado.android.utils.HumidityDeserializer;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.Interceptor$Chain;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestServiceGenerator {
    private static final long CACHE_SIZE = 10485760;
    private static final int CONNECT_TIMEOUT_SECONDS = 30;
    public static final String DEMO_SERVER_PATH = "http://localhost:8080";
    private static final int PORT = 8080;
    private static final int READ_TIMEOUT_SECONDS = 30;
    private static final int WRITE_TIMEOUT_SECONDS = 30;
    private static DemoServer demoServer;
    private static HvacToolApi hvacToolApiService;
    private static List<Interceptor> interceptorList = new LinkedList();
    private static OkHttpClient okHttpClient;
    private static TadoApiService tadoApiService;
    private static InstallationApi tadoInstallationApiService;
    private static TadoLocationApi tadoLocationApiService;

    static class C10742 implements Interceptor {
        C10742() {
        }

        public Response intercept(Interceptor$Chain chain) throws IOException {
            Request request = chain.request();
            if (request.method().equals(HttpRequest.METHOD_GET)) {
                if (Util.isNetworkAvailable()) {
                    request = request.newBuilder().header(HttpRequest.HEADER_CACHE_CONTROL, "public, max-age=604800").build();
                } else {
                    request = request.newBuilder().header(HttpRequest.HEADER_CACHE_CONTROL, "public, only-if-cached, max-stale=604800").build();
                }
            }
            Response originalResponse = chain.proceed(request);
            Builder builder = originalResponse.newBuilder();
            if (originalResponse.headers().names().contains(HttpRequest.HEADER_CACHE_CONTROL)) {
                for (String value : originalResponse.headers().values(HttpRequest.HEADER_CACHE_CONTROL)) {
                    if (value.contains("max-age")) {
                        builder.removeHeader("Cache-control");
                        builder.addHeader("Cache-control", value);
                        break;
                    }
                }
            }
            return builder.build();
        }
    }

    static class C10764 implements Interceptor {
        C10764() {
        }

        public Response intercept(Interceptor$Chain chain) throws IOException {
            Request request = chain.request();
            Builder response = new Builder();
            MockDataPair template = TemplateMatcher.INSTANCE.getTemplateForRequest(request, MockDataSource.INSTANCE.getLoadedTemplates());
            if (template != null) {
                response.body(ResponseBody.create(MediaType.parse("application/json"), template.getResponse().getBodyAsJsonString())).request(chain.request()).protocol(Protocol.HTTP_1_1).code(template.getResponse().getStatus()).message("mock call");
                return response.build();
            }
            throw new IOException("Template not defined");
        }
    }

    private RestServiceGenerator() {
    }

    private static void startDemoServer() {
        if (UserConfig.getServerAddress().equalsIgnoreCase(DEMO_SERVER_PATH)) {
            demoServer = new DemoServer(PORT);
            try {
                demoServer.start();
                MarketingAlertsManager.INSTANCE.startDemo();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static TadoApiService getTadoRestService() {
        if (tadoApiService == null) {
            startDemoServer();
            tadoApiService = (TadoApiService) getTadoRestService(TadoApiService.class, UserConfig.getServerAddress(), null, null);
        }
        return tadoApiService;
    }

    public static TadoApiService getTadoRestService(String serverAddress, Map<String, String> credentials) {
        if (tadoApiService == null) {
            tadoApiService = (TadoApiService) getTadoRestService(TadoApiService.class, serverAddress, credentials, null);
        }
        return tadoApiService;
    }

    public static InstallationApi getTadoInstallationRestService(String serverAddress, Map<String, String> credentials) {
        if (tadoInstallationApiService == null) {
            tadoInstallationApiService = (InstallationApi) getTadoRestService(InstallationApi.class, serverAddress, credentials, null);
        }
        return tadoInstallationApiService;
    }

    public static TadoLocationApi getTadoLocationRestService(String serverAddress, Map<String, String> credentials, OkHttpClient client) {
        if (tadoLocationApiService == null) {
            tadoLocationApiService = (TadoLocationApi) getTadoRestService(TadoLocationApi.class, serverAddress, credentials, client);
        }
        return tadoLocationApiService;
    }

    public static InstallationApi getTadoInstallationRestService() {
        return getTadoInstallationRestService(UserConfig.getServerAddress(), getCredentialsMap());
    }

    public static TadoLocationApi getTadoLocationRestService() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).retryOnConnectionFailure(false);
        clientBuilder.addInterceptor(getCredentialsInterceptor(getCredentialsMap()));
        return getTadoLocationRestService(UserConfig.getServerAddress(), getCredentialsMap(), clientBuilder.build());
    }

    public static HvacToolApi getHvacRestService(boolean debug) {
        return getHvacToolRestService(debug ? Constants.SERVER_HVAC_API_CI : Constants.SERVER_HVAC_API);
    }

    private static HvacToolApi getHvacToolRestService(String server) {
        if (hvacToolApiService == null) {
            hvacToolApiService = (HvacToolApi) getTadoRestService(HvacToolApi.class, server, null, null);
        }
        return hvacToolApiService;
    }

    private static <T> T getTadoRestService(Class<T> serviceClass, String baseUrl, Map<String, String> credentials, OkHttpClient client) {
        if (client == null) {
            client = getClient(credentials, TadoApplication.getTadoAppContext().getResources().getBoolean(C0676R.bool.logger));
        }
        return new Retrofit.Builder().baseUrl(baseUrl).client(client).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().registerTypeAdapter(DeviceType.class, new DeviceTypeDeserializer()).registerTypeAdapter(Date.class, new DateDeserializer()).registerTypeAdapter(Humidity.class, new HumidityDeserializer()).registerTypeAdapter(Installation.class, new InstallationClassAdapter()).registerTypeAdapter(GenericZoneSetting.class, new ZoneSettingClassAdapter()).registerTypeAdapter(GenericAwayConfiguration.class, new AwayConfigurationAdapter()).addSerializationExclusionStrategy(new GenericExclusionStrategy(Observable.class)).setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").setLenient().create())).build().create(serviceClass);
    }

    public static OkHttpClient getClient(Map<String, String> credentials, boolean withLogging) {
        if (okHttpClient != null) {
            return okHttpClient;
        }
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.readTimeout(1, TimeUnit.MINUTES);
        client.connectTimeout(30, TimeUnit.SECONDS);
        if (credentials != null) {
            client.addInterceptor(getCredentialsInterceptor(credentials));
        }
        if (TadoApplication.getTadoAppContext().getResources().getBoolean(C0676R.bool.cache)) {
            client.addNetworkInterceptor(new C10742());
            client.cache(new Cache(new File(TadoApplication.getTadoAppContext().getCacheDir(), "cache"), CACHE_SIZE));
        }
        if (interceptorList != null) {
            for (Interceptor interceptor : interceptorList) {
                client.addInterceptor(interceptor);
            }
        }
        return client.build();
    }

    private static void addInterceptor(Interceptor interceptor) {
        interceptorList.add(interceptor);
    }

    @NonNull
    private static Interceptor getCredentialsInterceptor(final Map<String, String> credentials) {
        return new Interceptor() {
            public Response intercept(Interceptor$Chain chain) throws IOException {
                Request request = chain.request();
                return chain.proceed(request.newBuilder().url(request.url().newBuilder().addQueryParameter(Constants.KEY_EXTRA_USERNAME, (String) credentials.get(Constants.KEY_EXTRA_USERNAME)).addQueryParameter(Constants.KEY_EXTRA_PASSWORD, (String) credentials.get(Constants.KEY_EXTRA_PASSWORD)).build()).build());
            }
        };
    }

    public static void prepareMockService() {
        addInterceptor(getMockTadoServiceInterceptor());
        UserConfig.setServerAddress("http://mock/");
    }

    private static Interceptor getMockTadoServiceInterceptor() {
        return new C10764();
    }

    public static Map<String, String> getCredentialsMap() {
        Map<String, String> options = new HashMap();
        options.put(Constants.KEY_EXTRA_USERNAME, UserConfig.getUsername());
        options.put(Constants.KEY_EXTRA_PASSWORD, UserConfig.getPassword());
        return options;
    }

    public static void destroyClient() {
        tadoApiService = null;
        tadoInstallationApiService = null;
        tadoLocationApiService = null;
        okHttpClient = null;
        hvacToolApiService = null;
        destroyDemoServer();
    }

    public static void destroyHvacToolClient() {
        hvacToolApiService = null;
    }

    public static void destroyDemoServer() {
        if (demoServer != null) {
            demoServer.stop();
            demoServer = null;
        }
    }
}
