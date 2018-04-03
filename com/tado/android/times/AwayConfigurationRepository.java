package com.tado.android.times;

import com.tado.android.rest.callback.RetryCallback;
import com.tado.android.rest.callback.presenters.PresenterDelegate;
import com.tado.android.rest.model.GenericAwayConfiguration;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.rest.service.TadoApiService;
import com.tado.android.settings.interfaces.GenericCallbackListener;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Observable;
import retrofit2.Call;
import retrofit2.Response;

class AwayConfigurationRepository extends Observable {
    private static volatile AwayConfigurationRepository instance;
    private TadoApiService api;
    private Map<String, GenericAwayConfiguration> cache = new HashMap();

    private AwayConfigurationRepository(TadoApiService api) {
        this.api = api;
    }

    public static AwayConfigurationRepository getInstance(TadoApiService api) {
        if (instance == null) {
            instance = new AwayConfigurationRepository(api);
        }
        return instance;
    }

    void getAwayConfiguration(int homeId, int zoneId, PresenterDelegate delegate) {
        String key = getKey(homeId, zoneId);
        if (this.cache.containsKey(key)) {
            setChanged();
            notifyObservers(this.cache.get(key));
            return;
        }
        requestAwayConfiguration(homeId, zoneId, delegate);
    }

    private String getKey(int homeId, int zoneId) {
        return String.format(Locale.US, "%d_%d", new Object[]{Integer.valueOf(homeId), Integer.valueOf(zoneId)});
    }

    private void setAwayConfiguration(int homeId, int zoneId, GenericAwayConfiguration awayConfiguration) {
        this.cache.put(getKey(homeId, zoneId), awayConfiguration);
        setChanged();
        notifyObservers(awayConfiguration);
    }

    private void requestAwayConfiguration(final int homeId, final int zoneId, PresenterDelegate delegate) {
        this.api.getAwayConfiguration(homeId, zoneId, RestServiceGenerator.getCredentialsMap()).enqueue(new RetryCallback<GenericAwayConfiguration>(delegate) {
            public void onResponse(Call<GenericAwayConfiguration> call, Response<GenericAwayConfiguration> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    AwayConfigurationRepository.this.setAwayConfiguration(homeId, zoneId, (GenericAwayConfiguration) response.body());
                }
            }

            public void onFailure(Call<GenericAwayConfiguration> call, Throwable t) {
                super.onFailure(call, t);
            }
        });
    }

    void updateAwaySettingsModel(int homeId, int zoneId, GenericAwayConfiguration awayConfiguration, PresenterDelegate delegate, GenericCallbackListener<Void> errorHandlingCallback) {
        final int i = homeId;
        final int i2 = zoneId;
        final GenericAwayConfiguration genericAwayConfiguration = awayConfiguration;
        this.api.updateAwayConfiguration(homeId, zoneId, awayConfiguration, RestServiceGenerator.getCredentialsMap()).enqueue(new RetryCallback<Void>(delegate, errorHandlingCallback) {
            public void onResponse(Call<Void> call, Response<Void> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    AwayConfigurationRepository.this.cache.put(AwayConfigurationRepository.this.getKey(i, i2), genericAwayConfiguration);
                }
            }

            public void onFailure(Call<Void> call, Throwable t) {
                super.onFailure(call, t);
            }
        });
    }

    public void release() {
        this.cache.clear();
        instance = null;
    }
}
