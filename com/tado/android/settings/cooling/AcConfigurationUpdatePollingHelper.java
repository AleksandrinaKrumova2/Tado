package com.tado.android.settings.cooling;

import android.os.Handler;
import com.squareup.otto.Subscribe;
import com.tado.android.app.TadoApplication;
import com.tado.android.controllers.ZoneCapabilitiesLoadedEvent;
import com.tado.android.controllers.ZoneController;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.AirConditioningControl;
import com.tado.android.rest.model.WirelessRemote;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.Constants;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import java.util.concurrent.atomic.AtomicBoolean;
import retrofit2.Call;
import retrofit2.Response;

public class AcConfigurationUpdatePollingHelper {
    private AcConfigurationUpdatePollingHelperInterface caller;
    private Handler handler = null;
    private AtomicBoolean pollRequestOpen = new AtomicBoolean(false);
    private int zoneId;

    class C10881 implements Runnable {
        C10881() {
        }

        public void run() {
            AcConfigurationUpdatePollingHelper.this.pollStatus();
            AcConfigurationUpdatePollingHelper.this.handler.postDelayed(this, Constants.CHECK_FOR_SERVER_CONNECTION_TIMEOUT);
        }
    }

    class C10892 extends TadoCallback<AirConditioningControl> {
        C10892() {
        }

        public void onResponse(Call<AirConditioningControl> call, Response<AirConditioningControl> response) {
            super.onResponse(call, response);
            AcConfigurationUpdatePollingHelper.this.pollRequestOpen.set(false);
            if (response.isSuccessful()) {
                AcConfigurationUpdatePollingHelper.this.processCommandTableUploadState(response);
            }
        }

        public void onFailure(Call<AirConditioningControl> call, Throwable t) {
            super.onFailure(call, t);
            AcConfigurationUpdatePollingHelper.this.pollRequestOpen.set(false);
        }
    }

    public AcConfigurationUpdatePollingHelper(int zoneId, AcConfigurationUpdatePollingHelperInterface caller) {
        TadoApplication.getBus().register(this);
        this.handler = new Handler();
        this.zoneId = zoneId;
        this.caller = caller;
    }

    public void cleanup() {
        TadoApplication.getBus().unregister(this);
        if (this.handler != null) {
            this.handler.removeCallbacksAndMessages(null);
        }
    }

    public void callDeviceCheckStatus() {
        this.handler.postDelayed(new C10881(), Constants.CHECK_FOR_SERVER_CONNECTION_TIMEOUT);
        this.caller.onInProgress();
    }

    private void pollStatus() {
        if (Util.isNetworkAvailable() && this.pollRequestOpen.compareAndSet(false, true)) {
            RestServiceGenerator.getTadoRestService().getZoneControl(UserConfig.getHomeId(), this.zoneId, RestServiceGenerator.getCredentialsMap()).enqueue(new C10892());
        }
    }

    private void processCommandTableUploadState(Response<AirConditioningControl> response) {
        WirelessRemote wirelessRemote = ((AirConditioningControl) response.body()).getWirelessRemote();
        if (wirelessRemote != null) {
            String commandTableUploadState = wirelessRemote.getCommandTableUploadState();
            Object obj = -1;
            switch (commandTableUploadState.hashCode()) {
                case -604548089:
                    if (commandTableUploadState.equals("IN_PROGRESS")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 108966002:
                    if (commandTableUploadState.equals("FINISHED")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 2066319421:
                    if (commandTableUploadState.equals("FAILED")) {
                        obj = null;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    onFailedUploadCommandSet();
                    return;
                case 1:
                    onFinishedUploadCommandSet();
                    return;
                default:
                    return;
            }
        }
    }

    private void onFinishedUploadCommandSet() {
        this.pollRequestOpen.set(true);
        if (this.zoneId == ZoneController.INSTANCE.getCurrentZoneId()) {
            ZoneController.INSTANCE.callZoneCapabilities(UserConfig.getCurrentZone().intValue());
        } else {
            this.caller.onSuccess();
        }
    }

    private void onFailedUploadCommandSet() {
        this.caller.onFailed();
    }

    public boolean isUpdateInProgress() {
        return !this.pollRequestOpen.get();
    }

    @Subscribe
    public void getZoneCapabilitiesLoadedEvent(ZoneCapabilitiesLoadedEvent e) {
        this.caller.onSuccess();
    }
}
