package com.tado.android.rest.model;

import com.tado.android.rest.model.installation.Installation;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.UserConfig;
import java.util.List;
import java.util.concurrent.Callable;
import retrofit2.Call;
import retrofit2.Response;

public class InstallationsResponseWrapper implements ResponseWrapper {
    private Call<List<Installation>> call;
    private Response<List<Installation>> response;

    static class C10661 implements Callable<Object> {
        C10661() {
        }

        public Object call() throws Exception {
            Call<List<Installation>> call = RestServiceGenerator.getTadoInstallationRestService().listInstallations(UserConfig.getHomeId());
            return new InstallationsResponseWrapper(call, call.execute());
        }
    }

    private InstallationsResponseWrapper(Call<List<Installation>> call, Response<List<Installation>> response) {
        this.call = call;
        this.response = response;
    }

    public Response<List<Installation>> getResponse() {
        return this.response;
    }

    public Call<List<Installation>> getCall() {
        return this.call;
    }

    public static Callable<Object> getCallable() {
        return new C10661();
    }
}
