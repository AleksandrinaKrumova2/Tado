package com.tado.android.times;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.tado.C0676R;
import com.tado.android.controllers.CapabilitiesController;
import com.tado.android.rest.model.GenericAwayConfiguration;
import com.tado.android.rest.model.HomeInfo.LicenseEnum;
import com.tado.android.rest.model.HotWaterAwayConfiguration;
import com.tado.android.rest.model.Temperature;
import com.tado.android.rest.model.installation.GenericZoneSetting;
import com.tado.android.rest.model.installation.GenericZoneSetting.TypeEnum;
import com.tado.android.rest.model.installation.HotWaterZoneSetting;
import com.tado.android.times.view.CommunicationView;
import com.tado.android.times.view.model.ModeEnum;
import com.tado.android.utils.ResourceFactory;
import com.tado.android.utils.UserConfig;
import com.tado.android.views.TadoZoneSettingViewConfiguration;
import com.tado.android.views.progressbar.ProgressBarComponent;
import java.util.Observable;
import java.util.Observer;

public class HotWaterAwayConfigurationFragment extends BaseAwayConfigurationFragment {
    @BindView(2131296345)
    TextView awayTempDesc;
    @BindView(2131297077)
    TextView awayTemperatureTextView;
    @BindView(2131296429)
    CommunicationView communicationView;
    private HotWaterAwayConfiguration mAwayConfiguration;
    @BindView(2131296343)
    LinearLayout mAwayTemperatureButton;
    @BindView(2131296880)
    ProgressBarComponent mProgressBar;
    Observer onAwayConfigurationChanged = new C11991();
    private Unbinder unbinder;

    class C11991 implements Observer {
        C11991() {
        }

        public void update(Observable o, Object awayConfiguration) {
            HotWaterAwayConfigurationFragment.this.bindViews(HotWaterAwayConfigurationFragment.this.mAwayConfiguration);
            HotWaterAwayConfigurationFragment.this.setupCountDownTimer(HotWaterAwayConfigurationFragment.this.mAwayConfiguration);
        }
    }

    class C12002 implements OnClickListener {
        C12002() {
        }

        public void onClick(View v) {
            TadoZoneSettingViewConfiguration viewConfiguration = CapabilitiesController.INSTANCE.getZoneSettingViewConfiguration();
            viewConfiguration.setDefaultModeSetting(ModeEnum.HOT_WATER, HotWaterAwayConfigurationFragment.this.getAwayHotWaterDefaultSetting());
            HotWaterAwayConfigurationFragment.this.showTemperatureView(viewConfiguration, HotWaterAwayConfigurationFragment.this.mAwayConfiguration);
        }
    }

    public static HotWaterAwayConfigurationFragment newInstance() {
        return new HotWaterAwayConfigurationFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0676R.layout.fragment_hot_water_away_configuration, container, false);
        this.unbinder = ButterKnife.bind((Object) this, view);
        initViews();
        return view;
    }

    private void initViews() {
        boolean z;
        boolean nonpremium;
        int i = 0;
        CommunicationView communicationView = this.communicationView;
        if (UserConfig.getLicense() != LicenseEnum.NON_PREMIUM) {
            z = true;
        } else {
            z = false;
        }
        communicationView.configureAsHeatingAwayView(z);
        this.mAwayTemperatureButton.setOnClickListener(new C12002());
        if (UserConfig.getLicense() == LicenseEnum.NON_PREMIUM) {
            nonpremium = true;
        } else {
            nonpremium = false;
        }
        TextView textView = this.awayTempDesc;
        if (nonpremium) {
            i = 8;
        }
        textView.setVisibility(i);
    }

    private GenericZoneSetting getAwayHotWaterDefaultSetting() {
        HotWaterZoneSetting defaultZoneSetting = new HotWaterZoneSetting();
        defaultZoneSetting.setTemperature(Temperature.fromValue(CapabilitiesController.INSTANCE.getValueForHomeTemperatureUnit(55.0f, 130.0f)));
        return defaultZoneSetting;
    }

    protected void onAwayConfigurationReady(GenericAwayConfiguration awayConfiguration) {
        this.mAwayConfiguration = (HotWaterAwayConfiguration) awayConfiguration;
        bindViews(this.mAwayConfiguration);
        this.mAwayConfiguration.addObserver(this.onAwayConfigurationChanged);
    }

    private void bindViews(HotWaterAwayConfiguration awayConfiguration) {
        this.awayTemperatureTextView.setCompoundDrawablesWithIntrinsicBounds(ResourceFactory.getTintedDrawable(getContext(), C0676R.drawable.zone_list_device_hot_water, C0676R.color.zone_settings_icon_color), null, null, null);
        if (!awayConfiguration.getSetting().getPowerBoolean()) {
            this.awayTemperatureTextView.setText(C0676R.string.components_hotWaterSettingDisplay_offLabel);
        } else if (awayConfiguration.getSetting().getTemperature() != null) {
            this.awayTemperatureTextView.setText(awayConfiguration.getSetting().getTemperature().getFormattedTemperatureValue(CapabilitiesController.INSTANCE.getZoneTypeTemperatureStep(TypeEnum.HOT_WATER)));
        } else {
            this.awayTemperatureTextView.setText(C0676R.string.components_hotWaterSettingDisplay_onLabel);
        }
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

    public void onDestroyView() {
        this.unbinder.unbind();
        super.onDestroyView();
    }
}
