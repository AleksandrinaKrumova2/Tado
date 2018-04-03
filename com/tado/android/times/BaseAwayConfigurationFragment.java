package com.tado.android.times;

import android.os.CountDownTimer;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import com.tado.C0676R;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.callback.presenters.GeneralErrorSnackbarPresenter;
import com.tado.android.rest.model.GenericAwayConfiguration;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.settings.interfaces.GenericCallbackListener;
import com.tado.android.settings.model.ScheduleSettings;
import com.tado.android.utils.ConnectivityChangeListener;
import com.tado.android.utils.ConnectivityChangeListener.ConnectionChangeCallback;
import com.tado.android.utils.ConnectivityChangeListener.ConnectivityBroadcastReceiver;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UserConfig;
import com.tado.android.views.TadoZoneSettingViewConfiguration;
import com.tado.android.views.progressbar.ProgressBarComponent;
import java.util.Observable;
import java.util.Observer;
import retrofit2.Call;

public abstract class BaseAwayConfigurationFragment extends Fragment implements CallProgressCallback, ConnectionChangeCallback {
    private static final int POST_UPDATES_TIME_INTERVAL_IN_MILLIS = 600;
    private static final String TAG = "BAwayConfigFrag";
    private AwayConfigurationRepository awayConfigurationRepository;
    private PostTimer mPostChangesCountDownTimer;
    private Snackbar mSnackBar;
    private Observer observer = new C11811();
    ConnectivityBroadcastReceiver onInternetConnectivityChange;

    class C11811 implements Observer {
        C11811() {
        }

        public void update(Observable o, Object data) {
            if (data instanceof GenericAwayConfiguration) {
                BaseAwayConfigurationFragment.this.onAwayConfigurationReady((GenericAwayConfiguration) data);
                ScheduleSettings.saveAwayConfiguration(UserConfig.getCurrentZone().intValue(), (GenericAwayConfiguration) data);
            }
        }
    }

    private class PostTimer extends CountDownTimer {
        GenericAwayConfiguration configuration;

        class C11831 implements GenericCallbackListener<Void> {
            C11831() {
            }

            public void onSuccess(Void body) {
                if (BaseAwayConfigurationFragment.this.isAdded()) {
                    BaseAwayConfigurationFragment.this.finishProgress();
                }
            }

            public void onFailure() {
                if (BaseAwayConfigurationFragment.this.isAdded()) {
                    BaseAwayConfigurationFragment.this.finishProgress();
                }
            }
        }

        PostTimer(long millisInFuture, long countDownInterval, GenericAwayConfiguration configuration) {
            super(millisInFuture, countDownInterval);
            this.configuration = configuration;
        }

        public void onTick(long millisUntilFinished) {
        }

        public void onFinish() {
            BaseAwayConfigurationFragment.this.dismissSnackBar();
            BaseAwayConfigurationFragment.this.postChanges(this.configuration, BaseAwayConfigurationFragment.this, new C11831(), BaseAwayConfigurationFragment.this.getView());
        }
    }

    protected abstract ProgressBarComponent getProgressBar();

    protected abstract void onAwayConfigurationReady(GenericAwayConfiguration genericAwayConfiguration);

    public BaseAwayConfigurationFragment() {
        setRetainInstance(true);
        this.awayConfigurationRepository = AwayConfigurationRepository.getInstance(RestServiceGenerator.getTadoRestService());
    }

    public void onResume() {
        super.onResume();
        this.awayConfigurationRepository.addObserver(this.observer);
        getAwayConfiguration(UserConfig.getHomeId(), UserConfig.getCurrentZone().intValue(), this.awayConfigurationRepository);
        this.onInternetConnectivityChange = ConnectivityChangeListener.register(this);
        Snitcher.start().log(3, TAG, "registered connectivity change listener %d", Integer.valueOf(System.identityHashCode(this.onInternetConnectivityChange)));
    }

    public void onPause() {
        dismissSnackBar();
        this.awayConfigurationRepository.deleteObserver(this.observer);
        Snitcher.start().log(3, TAG, "unregistering connectivity change listener %d", Integer.valueOf(System.identityHashCode(this.onInternetConnectivityChange)));
        ConnectivityChangeListener.unregister(this.onInternetConnectivityChange);
        super.onPause();
    }

    public void onDestroy() {
        this.awayConfigurationRepository.release();
        super.onDestroy();
    }

    protected void getAwayConfiguration(int homeId, int zoneId, AwayConfigurationRepository repository) {
        repository.getAwayConfiguration(homeId, zoneId, new GeneralErrorSnackbarPresenter(getView()));
    }

    public void setupCountDownTimer(GenericAwayConfiguration awayConfiguration) {
        if (this.mPostChangesCountDownTimer == null) {
            this.mPostChangesCountDownTimer = new PostTimer(600, 600, awayConfiguration);
            this.mPostChangesCountDownTimer.start();
            return;
        }
        this.mPostChangesCountDownTimer.cancel();
        this.mPostChangesCountDownTimer.start();
    }

    private void dismissSnackBar() {
        if (this.mSnackBar != null) {
            this.mSnackBar.dismiss();
            this.mSnackBar = null;
        }
    }

    private void postChanges(GenericAwayConfiguration awayConfiguration, CallProgressCallback progressCallback, GenericCallbackListener<Void> errorHandlingCallback, View view) {
        progressCallback.showProgress();
        awayConfiguration.prepareModelForUpdate();
        this.awayConfigurationRepository.updateAwaySettingsModel(UserConfig.getHomeId(), UserConfig.getCurrentZone().intValue(), awayConfiguration, new GeneralErrorSnackbarPresenter(view), errorHandlingCallback);
    }

    public void showProgress() {
        getProgressBar().showView();
    }

    public void finishProgress() {
        getProgressBar().hideViewWithAnimation();
    }

    private void showSnackBar(final Call call, final TadoCallback callback, @StringRes int messageStringResourceId) {
        if (getView() != null) {
            this.mSnackBar = Snackbar.make(getView(), messageStringResourceId, -2);
            if (!(call == null || callback == null)) {
                this.mSnackBar.setAction((int) C0676R.string.retry, new OnClickListener() {
                    public void onClick(View v) {
                        callback.retry(call);
                    }
                });
            }
            this.mSnackBar.show();
        }
    }

    void showTemperatureView(TadoZoneSettingViewConfiguration viewConfiguration, GenericAwayConfiguration awayConfiguration) {
        AwayTemperatureBottomSheet.Instance.getInstance(awayConfiguration, viewConfiguration).show(getChildFragmentManager(), "bottom sheet");
    }

    public void onInternetConnected() {
        dismissSnackBar();
        getAwayConfiguration(UserConfig.getHomeId(), UserConfig.getCurrentZone().intValue(), this.awayConfigurationRepository);
    }

    public void onInternetDisconnected() {
        showSnackBar(null, null, C0676R.string.errors_noInternetConnection_message);
    }
}
