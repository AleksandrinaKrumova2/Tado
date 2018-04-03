package com.tado.android.installation.clc;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.VideoView;
import butterknife.BindView;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.utils.ResourceFactory;
import com.tado.android.utils.Util;

public class ThermostaticOperationIntroActivity extends ACInstallationBaseActivity {
    @BindView(2131297198)
    VideoView videoView;

    class C08861 implements OnCompletionListener {
        C08861() {
        }

        public void onCompletion(MediaPlayer mp) {
            ThermostaticOperationIntroActivity.this.videoView.start();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_thermostatic_intro);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_title);
        this.titleTemplateTextview.setText(C0676R.string.installation_sacc_setupAC_clcRecording_thermostaticTempControl_title);
        this.textView.setText(Util.getText(this, C0676R.string.installation_sacc_setupAC_clcRecording_thermostaticTempControl_message, new Object[0]));
        this.textView.setMovementMethod(LinkMovementMethod.getInstance());
        this.proceedButton.setText(C0676R.string.installation_sacc_setupAC_clcRecording_thermostaticTempControl_confirmButton);
        this.videoView.setVideoURI(ResourceFactory.getUriToResource(this, C0676R.raw.clc));
        this.videoView.setOnCompletionListener(new C08861());
    }

    protected void onResume() {
        super.onResume();
        this.videoView.start();
    }

    public void proceedClick(View view) {
        InstallationProcessController.startActivity((Activity) this, TeachSmartACActivity.class, false);
    }
}
