package com.tado.android.installation;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tado.BuildConfig;
import com.tado.C0676R;
import com.tado.android.demo.marketing.MarketingAlertTypeEnum;
import com.tado.android.demo.marketing.MarketingAlertsManager;
import com.tado.android.installation.registerwr.InstallCoolingOverviewActivity;
import com.tado.android.installation.srt.common.DevicesWrapper;
import com.tado.android.installation.srt.common.SrtInstallationActivity;
import com.tado.android.rest.model.installation.GenericHardwareDevice;

public class ChooseProductActivity extends ACInstallationBaseActivity {
    @BindView(2131296871)
    LinearLayout buProductLayout;
    @BindView(2131296872)
    LinearLayout ekProductLayout;
    @BindView(2131296873)
    View ibProductLayout;
    @BindView(2131297149)
    Toolbar mToolbar;
    @BindView(2131296874)
    View saccProductLayout;
    @BindView(2131296875)
    View srtProductLayout;
    @BindView(2131296876)
    LinearLayout stProductLayout;

    class C07871 implements OnClickListener {
        C07871() {
        }

        public void onClick(View v) {
            ChooseProductActivity.this.onBackPressed();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.fragment_choose_product_activity);
        ButterKnife.bind((Activity) this);
        this.titleBarTextview.setText(C0676R.string.installation_productSelection_title);
        this.titleTemplateTextview.setText(C0676R.string.installation_productSelection_message);
        InstallationProcessController.getInstallationProcessController().setInstallation(null);
        configureVisibilities();
        this.mToolbar.setNavigationIcon((int) C0676R.drawable.ic_arrow_back_white_24dp);
        this.mToolbar.setNavigationOnClickListener(new C07871());
        MarketingAlertsManager.INSTANCE.featureSeen(MarketingAlertTypeEnum.PRODUCT_FINDER);
    }

    private void configureVisibilities() {
        int i = 8;
        if (BuildConfig.FLAVOR.equalsIgnoreCase("hager")) {
            this.buProductLayout.setVisibility(8);
            this.saccProductLayout.setVisibility(8);
            this.srtProductLayout.setVisibility(8);
        } else if (BuildConfig.FLAVOR.equalsIgnoreCase("gnf")) {
            this.buProductLayout.setVisibility(8);
        }
        GenericHardwareDevice firstGatewayDevice = new DevicesWrapper(InstallationProcessController.getInstallationProcessController().getInstallationInfo().getDevices()).getFirstNonGW01GatewayDevice();
        View view = this.ibProductLayout;
        if (firstGatewayDevice != null) {
            i = 0;
        }
        view.setVisibility(i);
    }

    public void chooseHeating(View view) {
        InstallationProcessController.startActivity((Activity) this, InstallHeatingOverviewActivity.class, false);
    }

    public void chooseSrt(View view) {
        InstallationProcessController.startActivity((Activity) this, SrtInstallationActivity.class, false);
    }

    public void chooseEk(View view) {
        InstallationProcessController.startActivity((Activity) this, InstallEkOverviewActivity.class, false);
    }

    public void chooseCooling(View view) {
        InstallationProcessController.startActivity((Activity) this, InstallCoolingOverviewActivity.class, false);
    }

    public void chooseConnector(View view) {
        InstallationProcessController.startActivity((Activity) this, InstallConnectorKitActivity.class, false);
    }

    public void chooseIB(View view) {
        InstallationProcessController.startActivity((Activity) this, InstallInternetBridgeReplacementActivity.class, false);
    }
}
