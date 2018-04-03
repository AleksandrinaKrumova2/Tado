package com.tado.android.installation.srt.common;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff.Mode;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.tado.C0676R;
import com.tado.android.dialogs.AlertChoiceDialogListener;
import com.tado.android.dialogs.AlertDialogs;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.hvac.BridgeReplacementInformation;
import com.tado.android.rest.model.hvac.BridgeReplacementInformation.ReconnectionTimeout;
import com.tado.android.rest.model.hvac.BridgeReplacementInstallation;
import com.tado.android.rest.model.hvac.ReconnectionStateEnum;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UserConfig;
import java.lang.ref.WeakReference;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

public class InstallationReconnectDevicesFragment extends Fragment {
    public static final String KEY_BRIDGE_REPLACEMENT = "keyBridgeReplacement";
    private static final int POLLING_FREQUENCY_MILLISECONS = 5000;
    @BindView(2131296558)
    RecyclerView deviceConnectionStateList;
    private DevicesConnectionAdapter devicesConnectionAdapter;
    @BindView(2131296584)
    Button doneButton;
    private PollingHandler handler;
    private BridgeReplacementInstallation installation;
    @BindView(2131296706)
    TextView learnMoreTextView;
    private Polling polling = new Polling();
    private boolean pollingEnabled = false;
    @BindView(2131296563)
    ImageView reconnectStateImageView;
    @BindView(2131296562)
    ProgressBar reconnectStateProgress;
    @BindView(2131296904)
    TextView reconnectStateTextView;
    private ReconnectionTimer timer;
    @BindView(2131297133)
    TextView timerTextView;
    private Unbinder unbinder;

    private class Polling implements Runnable {

        class C09601 extends TadoCallback<BridgeReplacementInstallation> {
            C09601() {
            }

            public void onResponse(Call<BridgeReplacementInstallation> call, Response<BridgeReplacementInstallation> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    InstallationReconnectDevicesFragment.this.installation = (BridgeReplacementInstallation) response.body();
                    Snitcher.start().log(3, "installation", "state: %s", InstallationReconnectDevicesFragment.this.installation.getBridgeReplacementInformation().reconnectionState);
                    if (InstallationReconnectDevicesFragment.this.isAdded()) {
                        InstallationReconnectDevicesFragment.this.updateConnectionState(InstallationReconnectDevicesFragment.this.installation.getBridgeReplacementInformation());
                    }
                } else if (this.serverError != null) {
                    Snitcher.start().log(6, "installation", "%s %s", this.serverError.getCode(), this.serverError.getTitle());
                }
            }

            public void onFailure(Call<BridgeReplacementInstallation> call, Throwable t) {
                super.onFailure(call, t);
                Snitcher.start().log(6, "installation", "error", new Object[0]);
                InstallationReconnectDevicesFragment.this.handler.poll();
            }
        }

        private Polling() {
        }

        public void run() {
            RestServiceGenerator.getTadoInstallationRestService().confirmBridgeConnection(UserConfig.getHomeId(), InstallationReconnectDevicesFragment.this.installation.getId().intValue()).enqueue(new C09601());
        }
    }

    private static class PollingHandler extends Handler {
        private int frequency;
        private boolean pollingEnabled;
        private Runnable pollingRunnable;

        PollingHandler(Runnable pollingRunnable, int frequencyMilliseconds) {
            this.pollingRunnable = pollingRunnable;
            this.frequency = frequencyMilliseconds;
        }

        void startPolling() {
            this.pollingEnabled = true;
            post(this.pollingRunnable);
        }

        void stopPolling() {
            this.pollingEnabled = false;
            removeCallbacks(this.pollingRunnable);
        }

        void poll() {
            if (this.pollingEnabled) {
                postDelayed(this.pollingRunnable, (long) this.frequency);
            }
        }
    }

    private class ReconnectionTimer extends CountDownTimer {
        WeakReference<TextView> timerTextView;

        ReconnectionTimer(long millisInFuture, long countDownInterval, TextView view) {
            super(millisInFuture, countDownInterval);
            this.timerTextView = new WeakReference(view);
        }

        public void onTick(long millisUntilFinished) {
            String value = DateUtils.formatElapsedTime(millisUntilFinished / 1000);
            if (this.timerTextView.get() != null) {
                ((TextView) this.timerTextView.get()).setText(value);
            }
        }

        public void onFinish() {
            if (InstallationReconnectDevicesFragment.this.pollingEnabled) {
                InstallationReconnectDevicesFragment.this.handler.post(InstallationReconnectDevicesFragment.this.polling);
            }
        }
    }

    public static InstallationReconnectDevicesFragment newInstance(BridgeReplacementInstallation installation) {
        InstallationReconnectDevicesFragment fragment = new InstallationReconnectDevicesFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_BRIDGE_REPLACEMENT, installation);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(C0676R.layout.fragment_installation_reconnect_devices, container, false);
        this.unbinder = ButterKnife.bind((Object) this, view);
        this.deviceConnectionStateList.setHasFixedSize(true);
        this.deviceConnectionStateList.setLayoutManager(new LinearLayoutManager(getContext()));
        if (this.installation != null) {
            prepareList(this.installation.getBridgeReplacementInformation().getBridgeConnectedDevices());
            updateConnectionState(this.installation.getBridgeReplacementInformation());
            this.reconnectStateTextView.setText(C0676R.string.installation_reconnectDevices_initialMessage);
            this.reconnectStateImageView.setVisibility(8);
            this.reconnectStateProgress.setVisibility(0);
            if (VERSION.SDK_INT < 21 && this.reconnectStateProgress.getIndeterminateDrawable() != null) {
                this.reconnectStateProgress.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getContext(), C0676R.color.ac_home), Mode.SRC_IN);
            }
            this.doneButton.setEnabled(false);
        }
        return view;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.installation = (BridgeReplacementInstallation) getArguments().getSerializable(KEY_BRIDGE_REPLACEMENT);
            this.handler = new PollingHandler(new Polling(), 5000);
        } else {
            InstallationProcessController.getInstallationProcessController().detectStatus(getActivity());
        }
        setHasOptionsMenu(true);
    }

    public void onResume() {
        super.onResume();
        this.handler.startPolling();
    }

    public void onPause() {
        super.onPause();
        this.handler.stopPolling();
    }

    private void prepareList(List<GenericHardwareDevice> devices) {
        this.devicesConnectionAdapter = new DevicesConnectionAdapter(devices);
        this.deviceConnectionStateList.setAdapter(this.devicesConnectionAdapter);
    }

    private void updateConnectionState(BridgeReplacementInformation installation) {
        if (installation.reconnectionState != null) {
            this.devicesConnectionAdapter.updateDevicesState(installation.getBridgeConnectedDevices(), installation.reconnectionState == ReconnectionStateEnum.FAILED);
            updateInfoPanel(installation.reconnectionState);
            switch (installation.reconnectionState) {
                case NOT_STARTED:
                case IN_PROGRESS:
                    updateTimer(installation.reconnectionTimeout);
                    this.doneButton.setEnabled(false);
                    this.handler.poll();
                    return;
                case FAILED:
                    this.handler.poll();
                    return;
                case FINISHED:
                    this.handler.stopPolling();
                    return;
                default:
                    return;
            }
        }
    }

    private void updateInfoPanel(ReconnectionStateEnum reconnectionState) {
        switch (reconnectionState) {
            case NOT_STARTED:
            case IN_PROGRESS:
                this.reconnectStateTextView.setText(C0676R.string.installation_reconnectDevices_initialMessage);
                this.reconnectStateImageView.setVisibility(8);
                this.reconnectStateProgress.setVisibility(0);
                this.timerTextView.setVisibility(0);
                return;
            case FAILED:
                this.reconnectStateTextView.setText(C0676R.string.installation_reconnectDevices_unsuccessfulMessage);
                this.reconnectStateTextView.setMovementMethod(new LinkMovementMethod());
                this.reconnectStateImageView.setVisibility(0);
                this.reconnectStateImageView.setImageResource(C0676R.drawable.ic_exclamation_mark);
                this.reconnectStateProgress.setVisibility(8);
                this.doneButton.setEnabled(true);
                this.timerTextView.setVisibility(8);
                return;
            case FINISHED:
                this.reconnectStateTextView.setText(C0676R.string.installation_reconnectDevices_successfulMessage);
                this.reconnectStateImageView.setVisibility(0);
                this.reconnectStateImageView.setImageResource(C0676R.drawable.ic_check_mark_green);
                this.reconnectStateProgress.setVisibility(8);
                this.doneButton.setEnabled(true);
                this.timerTextView.setVisibility(8);
                return;
            default:
                return;
        }
    }

    private void updateTimer(ReconnectionTimeout expires) {
        if (expires != null) {
            if (this.timer != null) {
                this.timer.cancel();
            }
            if (expires.remainingTimeInSeconds > 0) {
                this.timerTextView.setVisibility(0);
                this.timer = new ReconnectionTimer((long) (expires.remainingTimeInSeconds * 1000), 1000, this.timerTextView);
                this.timer.start();
                return;
            }
            this.timerTextView.setVisibility(8);
        }
    }

    @OnClick({2131296584})
    public void onDone() {
        if (this.installation == null || this.installation.getBridgeReplacementInformation().reconnectionState == ReconnectionStateEnum.NOT_STARTED || this.installation.getBridgeReplacementInformation().reconnectionState == ReconnectionStateEnum.IN_PROGRESS) {
            this.doneButton.setEnabled(false);
        } else {
            confirmBridgeReplacement(this.installation.getId().intValue());
        }
    }

    private void confirmBridgeReplacement(final int installationId) {
        RestServiceGenerator.getTadoInstallationRestService().completeBridgeReplacement(UserConfig.getHomeId(), installationId).enqueue(new TadoCallback<BridgeReplacementInstallation>() {

            class C09571 implements AlertChoiceDialogListener {
                C09571() {
                }

                public void OnOKClicked() {
                    InstallationReconnectDevicesFragment.this.confirmBridgeReplacement(installationId);
                }

                public void OnCancelClicked() {
                }
            }

            public void onResponse(Call<BridgeReplacementInstallation> call, Response<BridgeReplacementInstallation> response) {
                super.onResponse(call, response);
                InstallationProcessController.getInstallationProcessController().detectStatus(InstallationReconnectDevicesFragment.this.getActivity());
            }

            public void onFailure(Call<BridgeReplacementInstallation> call, Throwable t) {
                super.onFailure(call, t);
                if (InstallationReconnectDevicesFragment.this.isAdded()) {
                    Activity activity = InstallationReconnectDevicesFragment.this.getActivity();
                    AlertDialogs.showCancelRetryAlert(activity.getString(C0676R.string.installation_error_alert_title), activity.getString(C0676R.string.could_not_connect_to_server), activity.getString(C0676R.string.alert_retry), activity.getString(C0676R.string.alert_cancel), activity, new C09571());
                }
            }
        });
    }

    @OnClick({2131296706})
    void onLearnMore() {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getString(C0676R.string.installation_reconnectDevices_learnMoreURL))));
    }

    public void onDestroyView() {
        this.unbinder.unbind();
        super.onDestroyView();
    }
}
