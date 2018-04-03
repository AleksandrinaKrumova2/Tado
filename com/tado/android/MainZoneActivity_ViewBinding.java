package com.tado.android;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class MainZoneActivity_ViewBinding<T extends MainZoneActivity> implements Unbinder {
    protected T target;

    @UiThread
    public MainZoneActivity_ViewBinding(T target, View source) {
        this.target = target;
        target.mZoneFragmentPager = (ViewPager) Utils.findRequiredViewAsType(source, C0676R.id.zone_fragment_pager, "field 'mZoneFragmentPager'", ViewPager.class);
        target.mControlPanelLayout = (RelativeLayout) Utils.findRequiredViewAsType(source, C0676R.id.control_panel_layout, "field 'mControlPanelLayout'", RelativeLayout.class);
        target.loadingScreen = Utils.findRequiredView(source, C0676R.id.loading_screen, "field 'loadingScreen'");
        target.mBetaView = Utils.findRequiredView(source, C0676R.id.beta_text_view, "field 'mBetaView'");
        target.mProgressBar = (ProgressBar) Utils.findRequiredViewAsType(source, C0676R.id.pbHeaderProgress, "field 'mProgressBar'", ProgressBar.class);
        target.loadingLogo = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.imageViewLogo, "field 'loadingLogo'", ImageView.class);
        target.installationLayout = Utils.findRequiredView(source, C0676R.id.installation_layout, "field 'installationLayout'");
        target.installationTitle = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.installation_title, "field 'installationTitle'", TextView.class);
        target.installationMessage = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.installation_message, "field 'installationMessage'", TextView.class);
        target.installationButton = (Button) Utils.findRequiredViewAsType(source, C0676R.id.installation_button, "field 'installationButton'", Button.class);
        target.installationImage = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.installation_image, "field 'installationImage'", ImageView.class);
        target.bottomSheetRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0676R.id.bottom_sheet_recycler_view, "field 'bottomSheetRecyclerView'", RecyclerView.class);
        target.demoButton = (Button) Utils.findRequiredViewAsType(source, C0676R.id.demo_button, "field 'demoButton'", Button.class);
        target.bottomSheetParentView = Utils.findRequiredView(source, C0676R.id.bottom_sheet_coordinator_layout, "field 'bottomSheetParentView'");
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mZoneFragmentPager = null;
        target.mControlPanelLayout = null;
        target.loadingScreen = null;
        target.mBetaView = null;
        target.mProgressBar = null;
        target.loadingLogo = null;
        target.installationLayout = null;
        target.installationTitle = null;
        target.installationMessage = null;
        target.installationButton = null;
        target.installationImage = null;
        target.bottomSheetRecyclerView = null;
        target.demoButton = null;
        target.bottomSheetParentView = null;
        this.target = null;
    }
}
