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
import com.tado.android.rest.model.CoolingAwayConfiguration;
import com.tado.android.rest.model.GenericAwayConfiguration;
import com.tado.android.rest.model.Temperature;
import com.tado.android.rest.model.installation.CoolingZoneSetting;
import com.tado.android.rest.model.installation.GenericZoneSetting;
import com.tado.android.rest.model.installation.GenericZoneSetting.TypeEnum;
import com.tado.android.times.view.CommunicationView;
import com.tado.android.times.view.model.ModeEnum;
import com.tado.android.utils.ResourceFactory;
import com.tado.android.views.TadoZoneSettingViewConfiguration;
import com.tado.android.views.progressbar.ProgressBarComponent;
import java.util.Observable;
import java.util.Observer;

public class CoolingAwayConfigurationFragment extends BaseAwayConfigurationFragment {
    @BindView(2131297077)
    TextView awayTemperatureTextView;
    @BindView(2131296429)
    CommunicationView communicationView;
    private CoolingAwayConfiguration mAwayConfiguration;
    @BindView(2131296343)
    LinearLayout mAwayTemperatureButton;
    @BindView(2131296880)
    ProgressBarComponent mProgressBar;
    Observer onAwayConfigurationChanged = new C11841();
    private Unbinder unbinder;

    class C11841 implements Observer {
        C11841() {
        }

        public void update(Observable o, Object awayConfiguration) {
            CoolingAwayConfigurationFragment.this.setupCountDownTimer(CoolingAwayConfigurationFragment.this.mAwayConfiguration);
            CoolingAwayConfigurationFragment.this.bindViews(CoolingAwayConfigurationFragment.this.mAwayConfiguration);
        }
    }

    class C11852 implements OnClickListener {
        C11852() {
        }

        public void onClick(View v) {
            TadoZoneSettingViewConfiguration viewConfiguration = CapabilitiesController.INSTANCE.getZoneSettingViewConfiguration();
            viewConfiguration.setDefaultModeSetting(ModeEnum.COOL, CoolingAwayConfigurationFragment.this.getAwayCoolingDefaultSetting());
            CoolingAwayConfigurationFragment.this.showTemperatureView(viewConfiguration, CoolingAwayConfigurationFragment.this.mAwayConfiguration);
        }
    }

    public static CoolingAwayConfigurationFragment newInstance() {
        return new CoolingAwayConfigurationFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0676R.layout.fragment_cooling_away_configuration, container, false);
        this.unbinder = ButterKnife.bind((Object) this, view);
        initViews();
        return view;
    }

    private void initViews() {
        this.communicationView.configureAsCoolingAwayView();
        this.mAwayTemperatureButton.setOnClickListener(new C11852());
    }

    private GenericZoneSetting getAwayCoolingDefaultSetting() {
        CoolingZoneSetting defaultZoneSetting = new CoolingZoneSetting();
        defaultZoneSetting.setTemperature(Temperature.fromValue(CapabilitiesController.INSTANCE.getValueForHomeTemperatureUnit(16.0f, 61.0f)));
        return defaultZoneSetting;
    }

    protected void onAwayConfigurationReady(GenericAwayConfiguration awayConfiguration) {
        this.mAwayConfiguration = (CoolingAwayConfiguration) awayConfiguration;
        bindViews(this.mAwayConfiguration);
        this.mAwayConfiguration.addObserver(this.onAwayConfigurationChanged);
    }

    private void bindViews(CoolingAwayConfiguration awayConfiguration) {
        this.awayTemperatureTextView.setCompoundDrawablesWithIntrinsicBounds(ResourceFactory.getTintedDrawable(getContext(), ResourceFactory.getModeDrawableIcon(awayConfiguration.getSetting().getMode(), awayConfiguration.getSetting().getPowerBoolean()), C0676R.color.zone_settings_icon_color), null, null, null);
        if (!awayConfiguration.getSetting().getPowerBoolean()) {
            this.awayTemperatureTextView.setText(C0676R.string.components_hotWaterSettingDisplay_offLabel);
        } else if (awayConfiguration.getSetting().getTemperature() != null) {
            this.awayTemperatureTextView.setText(awayConfiguration.getSetting().getTemperature().getFormattedTemperatureValue(CapabilitiesController.INSTANCE.getZoneTypeTemperatureStep(TypeEnum.AIR_CONDITIONING)));
        } else if (awayConfiguration.getSetting().getMode() != null) {
            this.awayTemperatureTextView.setText(awayConfiguration.getSetting().getMode().toString());
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
