package com.tado.android.rest.service;

import com.tado.android.rest.model.GeolocationConfig;
import com.tado.android.rest.model.GeolocationUpdate;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TadoLocationApi {
    @GET("/api/v2/homes/{home_id}/mobileDevices/{mobile_device_id}/geolocationConfig")
    Call<GeolocationConfig> getLocationProviderConfig(@Path("home_id") int i, @Path("mobile_device_id") int i2);

    @PUT("/api/v2/homes/{home_id}/mobileDevices/{mobile_device_id}/geolocationFix")
    Call<Void> postLocationUpdate(@Path("home_id") int i, @Path("mobile_device_id") int i2, @Body GeolocationUpdate geolocationUpdate);
}
