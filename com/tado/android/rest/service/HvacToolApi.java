package com.tado.android.rest.service;

import com.tado.android.rest.model.hvac.InstallFlowStep;
import com.tado.android.rest.model.hvac.InstallationFlow;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface HvacToolApi {
    @GET("/installability/deviceInstallation")
    Call<InstallationFlow> getInstallFlow(@QueryMap Map<String, String> map);

    @GET("/flowEngine/step")
    Call<InstallFlowStep> getInstallFlowStep(@QueryMap Map<String, String> map);
}
