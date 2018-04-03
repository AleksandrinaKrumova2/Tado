package com.tado.android.installation.acsetup;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.installation.CommandTableUpload;
import com.tado.android.rest.model.installation.CommandTableUpload.StateEnum;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.Constants;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UserConfig;
import java.util.concurrent.atomic.AtomicBoolean;
import retrofit2.Call;
import retrofit2.Response;

public class CommandSetUpdateActivity extends ACInstallationBaseActivity {
    Handler handler = null;
    private AtomicBoolean pollRequestOpen = new AtomicBoolean(false);
    @BindView(2131297165)
    public TextView troubleshootingTextView;

    class C08321 implements Runnable {
        C08321() {
        }

        public void run() {
            CommandSetUpdateActivity.this.troubleshootingTextView.setVisibility(0);
        }
    }

    class C08332 implements Runnable {
        C08332() {
        }

        public void run() {
            CommandSetUpdateActivity.this.pollStatus();
            CommandSetUpdateActivity.this.handler.postDelayed(this, Constants.CHECK_FOR_SERVER_CONNECTION_TIMEOUT);
        }
    }

    class C08343 extends TadoCallback<CommandTableUpload> {
        C08343() {
        }

        public void onResponse(Call<CommandTableUpload> call, Response<CommandTableUpload> response) {
            super.onResponse(call, response);
            CommandSetUpdateActivity.this.pollRequestOpen.set(false);
            if (response.isSuccessful()) {
                CommandSetUpdateActivity.this.navigateToScreenForState(((CommandTableUpload) response.body()).getState());
                return;
            }
            CommandSetUpdateActivity.this.handleServerError(this.serverError, CommandSetUpdateActivity.this, call, response.code(), this);
        }

        public void onFailure(Call<CommandTableUpload> call, Throwable t) {
            super.onFailure(call, t);
            CommandSetUpdateActivity.this.pollRequestOpen.set(false);
            InstallationProcessController.showConnectionErrorRetrofit(CommandSetUpdateActivity.this, call, this);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_command_set_update);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_uploadCommandTable_title);
        this.titleTemplateTextview.setText(C0676R.string.installation_sacc_uploadCommandTable_updating_title);
        this.textView.setText(getString(C0676R.string.installation_sacc_uploadCommandTable_updating_message));
        this.centerImageOverlay.setImageResource(C0676R.drawable.device_ac_update);
        this.centerImageOverlay.setVisibility(0);
    }

    public void troubleshoot(View view) {
        Intent i = new Intent("android.intent.action.VIEW");
        i.setData(Uri.parse(getString(C0676R.string.installation_sacc_uploadCommandTable_updating_troubleshootingURL)));
        startActivity(i);
    }

    protected void onResume() {
        super.onResume();
        this.handler = new Handler();
        this.handler.postDelayed(new C08321(), Constants.WAIT_FOR_COMMANDSET_UPDATE_TIMEOUT);
        this.handler.postDelayed(new C08332(), Constants.CHECK_FOR_SERVER_CONNECTION_TIMEOUT);
    }

    private boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void pollStatus() {
        Snitcher.start().log("Polling Command Table Upload status", new Object[0]);
        if (isNetworkAvailable() && this.pollRequestOpen.compareAndSet(false, true)) {
            RestServiceGenerator.getTadoInstallationRestService().showCommandTableUpload(UserConfig.getHomeId(), Integer.valueOf(InstallationProcessController.getInstallationProcessController().getInstallationId())).enqueue(new C08343());
        }
    }

    public void navigateToScreenForState(StateEnum state) {
        switch (state) {
            case FAILED:
                Snitcher.start().log("Command table upload FAILED", new Object[0]);
                InstallationProcessController.startActivity((Activity) this, CommandSetUpdateTroubleshootingActivity.class, true);
                return;
            case FINISHED:
                Snitcher.start().log("Command table upload FINISHED", new Object[0]);
                InstallationProcessController.startActivity((Activity) this, ACSetupSuccessfulActivity.class, true);
                return;
            case IN_PROGRESS:
                Snitcher.start().log("Command table upload IN PROGRESS", new Object[0]);
                return;
            case NOT_STARTED:
                Snitcher.start().log("Command table upload NOT STARTED", new Object[0]);
                return;
            default:
                return;
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.handler != null) {
            this.handler.removeCallbacksAndMessages(null);
        }
    }
}
