package com.tado.android.times;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.tado.C0676R;
import com.tado.android.controllers.CapabilitiesController;
import com.tado.android.controllers.ZoneController;
import com.tado.android.premium.PremiumCarouselActivity;
import com.tado.android.rest.model.GenericAwayConfiguration;
import com.tado.android.rest.model.HeatingAwayConfiguration;
import com.tado.android.rest.model.HeatingAwayConfiguration.PreheatingLevel;
import com.tado.android.rest.model.HomeInfo.LicenseEnum;
import com.tado.android.rest.model.Temperature;
import com.tado.android.rest.model.installation.GenericZoneSetting.TypeEnum;
import com.tado.android.rest.model.installation.HeatingZoneSetting;
import com.tado.android.times.view.AwayCirclesView;
import com.tado.android.times.view.CommunicationView;
import com.tado.android.times.view.model.ModeEnum;
import com.tado.android.utils.UserConfig;
import com.tado.android.views.TadoZoneSettingViewConfiguration;
import com.tado.android.views.progressbar.ProgressBarComponent;
import java.util.Observable;
import java.util.Observer;

public class HeatingAwayConfigurationFragment extends BaseAwayConfigurationFragment {
    @BindView(2131296335)
    AwayCirclesView awayCirclesView;
    @BindView(2131296345)
    TextView awayTempDesc;
    @BindView(2131296346)
    TextView awayTempLabel;
    @BindView(2131296429)
    CommunicationView communicationView;
    HeatingAwayConfiguration mAwayConfiguration;
    @BindView(2131296343)
    LinearLayout mAwayTemperatureButton;
    @BindView(2131296336)
    TextView mBalancedTextView;
    @BindView(2131296340)
    TextView mComfortSettingsInfo;
    @BindView(2131296337)
    TextView mComfortTextView;
    @BindView(2131296338)
    TextView mEcoTextView;
    @BindView(2131296880)
    ProgressBarComponent mProgressBar;
    @BindView(2131296342)
    SeekBar mSeekBar;
    Observer onAwayConfigurationChanged = new C11951();
    @BindView(2131296858)
    ViewGroup preheatingLayout;
    @BindView(2131296859)
    Button premiumButton;
    @BindView(2131296860)
    ViewGroup premiumLayout;
    @BindView(2131297077)
    TextView temperatureValueTextView;
    private Unbinder unbinder;

    class C11951 implements Observer {
        C11951() {
        }

        public void update(Observable o, Object awayConfiguration) {
            if (UserConfig.getLicense() == LicenseEnum.NON_PREMIUM) {
                HeatingAwayConfigurationFragment.this.mAwayConfiguration.setPreheatingLevel(PreheatingLevel.OFF);
            }
            HeatingAwayConfigurationFragment.this.setupCountDownTimer(HeatingAwayConfigurationFragment.this.mAwayConfiguration);
            HeatingAwayConfigurationFragment.this.bindViews(HeatingAwayConfigurationFragment.this.mAwayConfiguration);
        }
    }

    class C11962 implements OnSeekBarChangeListener {
        C11962() {
        }

        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            HeatingAwayConfigurationFragment.this.updateInfoText(seekBar.getProgress());
            HeatingAwayConfigurationFragment.this.awayCirclesView.setLevel(progress);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            HeatingAwayConfigurationFragment.this.mAwayConfiguration.setPreheatingLevel(PreheatingLevel.valueOf(seekBar.getProgress()));
        }
    }

    class C11973 implements OnClickListener {
        C11973() {
        }

        public void onClick(View v) {
            TadoZoneSettingViewConfiguration viewConfiguration = CapabilitiesController.INSTANCE.getZoneSettingViewConfiguration();
            viewConfiguration.setDefaultModeSetting(ModeEnum.HEATING, HeatingAwayConfigurationFragment.this.getAwayHeatingDefaultSetting());
            HeatingAwayConfigurationFragment.this.showTemperatureView(viewConfiguration, HeatingAwayConfigurationFragment.this.mAwayConfiguration);
        }
    }

    class C11984 implements OnClickListener {
        C11984() {
        }

        public void onClick(View view) {
            HeatingAwayConfigurationFragment.this.startActivity(new Intent(HeatingAwayConfigurationFragment.this.getContext(), PremiumCarouselActivity.class));
        }
    }

    public static HeatingAwayConfigurationFragment newInstance() {
        return new HeatingAwayConfigurationFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0676R.layout.fragment_heating_away_configuration, container, false);
        this.unbinder = ButterKnife.bind((Object) this, view);
        initViews();
        return view;
    }

    public void onResume() {
        boolean nonpremium;
        int i;
        boolean z = true;
        int i2 = 8;
        super.onResume();
        if (UserConfig.getLicense() == LicenseEnum.NON_PREMIUM) {
            nonpremium = true;
        } else {
            nonpremium = false;
        }
        ViewGroup viewGroup = this.premiumLayout;
        if (nonpremium) {
            i = 0;
        } else {
            i = 8;
        }
        viewGroup.setVisibility(i);
        viewGroup = this.preheatingLayout;
        if (nonpremium) {
            i = 8;
        } else {
            i = 0;
        }
        viewGroup.setVisibility(i);
        TextView textView = this.awayTempDesc;
        if (!nonpremium) {
            i2 = 0;
        }
        textView.setVisibility(i2);
        this.awayTempLabel.setText(nonpremium ? C0676R.string.premiumUpgrade_smartScheduleAwayConfiguration_temperatureLabel : C0676R.string.smartSchedule_away_heating_minTemperatureLabel);
        if (nonpremium) {
            this.mSeekBar.setProgress(0);
        }
        CommunicationView communicationView = this.communicationView;
        if (nonpremium) {
            z = false;
        }
        communicationView.configureAsHeatingAwayView(z);
    }

    private void initViews() {
        boolean z = true;
        this.mSeekBar.setOnSeekBarChangeListener(new C11962());
        this.mSeekBar.setEnabled(this.mAwayConfiguration != null);
        LinearLayout linearLayout = this.mAwayTemperatureButton;
        if (this.mAwayConfiguration == null) {
            z = false;
        }
        linearLayout.setEnabled(z);
        this.mAwayTemperatureButton.setOnClickListener(new C11973());
        this.premiumButton.setOnClickListener(new C11984());
    }

    private HeatingZoneSetting getAwayHeatingDefaultSetting() {
        HeatingZoneSetting defaultZoneSetting = new HeatingZoneSetting();
        defaultZoneSetting.setTemperature(Temperature.fromValue(CapabilitiesController.INSTANCE.getValueForHomeTemperatureUnit(16.0f, 61.0f)));
        return defaultZoneSetting;
    }

    protected void onAwayConfigurationReady(GenericAwayConfiguration awayConfiguration) {
        this.mAwayConfiguration = (HeatingAwayConfiguration) awayConfiguration;
        initData(this.mAwayConfiguration);
        this.mAwayConfiguration.addObserver(this.onAwayConfigurationChanged);
    }

    private void initData(HeatingAwayConfiguration awayConfiguration) {
        this.mSeekBar.setProgress(awayConfiguration.getPreheatingLevel() != null ? awayConfiguration.getPreheatingLevel().getLevel() : PreheatingLevel.COMFORT.getLevel());
        bindViews(awayConfiguration);
    }

    protected ProgressBarComponent getProgressBar() {
        return this.mProgressBar;
    }

    public void onPause() {
        if (this.mAwayConfiguration != null) {
            this.mAwayConfiguration.deleteObservers();
        }
        super.onPause();
    }

    private void bindViews(HeatingAwayConfiguration awayConfiguration) {
        boolean z;
        boolean z2 = true;
        if (awayConfiguration.getTemperature() == null) {
            awayConfiguration.setTemperature(Temperature.fromValue(21.0f));
        }
        SeekBar seekBar = this.mSeekBar;
        if (this.mAwayConfiguration != null) {
            z = true;
        } else {
            z = false;
        }
        seekBar.setEnabled(z);
        LinearLayout linearLayout = this.mAwayTemperatureButton;
        if (this.mAwayConfiguration == null) {
            z2 = false;
        }
        linearLayout.setEnabled(z2);
        if (ZoneController.INSTANCE.getCurrentZone() != null) {
            this.temperatureValueTextView.setText(awayConfiguration.getTemperature().getFormattedTemperatureValue(CapabilitiesController.INSTANCE.getZoneTypeTemperatureStep(TypeEnum.HEATING)));
            this.awayCirclesView.setLevel(awayConfiguration.getPreheatingLevel().getLevel());
        }
    }

    private void updateInfoText(int level) {
        int textResource;
        switch (level) {
            case 1:
                textResource = C0676R.string.smartSchedule_away_heating_ecoDescription;
                break;
            case 2:
                textResource = C0676R.string.smartSchedule_away_heating_balanceDescription;
                break;
            case 3:
                textResource = C0676R.string.smartSchedule_away_heating_comfortDescription;
                break;
            default:
                textResource = C0676R.string.smartSchedule_away_heating_offDescription;
                break;
        }
        this.mComfortSettingsInfo.setText(getResources().getText(textResource));
    }

    public void onDestroyView() {
        this.unbinder.unbind();
        super.onDestroyView();
    }
}
