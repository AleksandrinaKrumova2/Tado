package com.tado.android.premium;

import com.squareup.otto.Subscribe;
import com.tado.android.app.TadoApplication;
import com.tado.android.mvp.common.BasePresenter;
import com.tado.android.mvp.views.ZoneView;
import com.tado.android.rest.model.HomeState;
import com.tado.android.rest.model.ZoneState;
import com.tado.android.utils.Snitcher;

public class UpsellingPresenter implements BasePresenter<ZoneView> {
    private static final String TAG = "UpsellingPresenter";
    private HomeState currentHomeState;
    private ZoneState currentZoneState;
    private int mZoneId;
    private ZoneView mZoneView;

    public void bindView(ZoneView view) {
        Snitcher.start().log(3, TAG, "%d bindView zone id %d", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(this.mZoneId));
        TadoApplication.getBus().register(this);
        this.mZoneView = view;
    }

    public void unbindView() {
        Snitcher.start().log(3, TAG, "%d unbindView zone id %d", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(this.mZoneId));
        TadoApplication.getBus().unregister(this);
        this.mZoneView = null;
    }

    public void setZoneId(int zoneId) {
        this.mZoneId = zoneId;
    }

    @Subscribe
    public void getZoneState(ZoneState zoneState) {
        Snitcher.start().log(3, TAG, "%d update zone state %d zone id %d", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(zoneState.getId()), Integer.valueOf(this.mZoneId));
        if (this.mZoneId == zoneState.getId()) {
            this.currentZoneState = zoneState;
            if (this.currentHomeState != null) {
                showUpsellingScreen();
            }
        }
    }

    @Subscribe
    public void getHomeState(HomeState homeState) {
        this.currentHomeState = homeState;
        if (this.currentZoneState != null) {
            showUpsellingScreen();
        }
    }

    public void clearStates() {
        this.currentHomeState = null;
        this.currentZoneState = null;
    }

    private void showUpsellingScreen() {
        this.mZoneView.showUpsellingScreen();
    }
}
