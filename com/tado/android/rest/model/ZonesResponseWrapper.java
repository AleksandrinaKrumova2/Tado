package com.tado.android.rest.model;

import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.UserConfig;
import java.util.List;
import java.util.concurrent.Callable;
import retrofit2.Call;
import retrofit2.Response;

public class ZonesResponseWrapper implements ResponseWrapper {
    private Call<List<Zone>> call;
    private Response<List<Zone>> response;

    static class C10731 implements Callable<Object> {
        C10731() {
        }

        public Object call() throws Exception {
            Call<List<Zone>> call = RestServiceGenerator.getTadoRestService().getZones(UserConfig.getHomeId(), RestServiceGenerator.getCredentialsMap());
            return new ZonesResponseWrapper(call, call.execute());
        }
    }

    private ZonesResponseWrapper(Call<List<Zone>> call, Response<List<Zone>> response) {
        this.call = call;
        this.response = response;
    }

    public Response<List<Zone>> getResponse() {
        return this.response;
    }

    public Call<List<Zone>> getCall() {
        return this.call;
    }

    public static Callable<Object> getCallable() {
        return new C10731();
    }
}
