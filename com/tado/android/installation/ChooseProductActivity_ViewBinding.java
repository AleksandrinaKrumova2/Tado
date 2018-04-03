package com.tado.android.installation;

import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class ChooseProductActivity_ViewBinding<T extends ChooseProductActivity> extends ACInstallationBaseActivity_ViewBinding<T> {
    @UiThread
    public ChooseProductActivity_ViewBinding(T target, View source) {
        super(target, source);
        target.stProductLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C0676R.id.product_st, "field 'stProductLayout'", LinearLayout.class);
        target.buProductLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C0676R.id.product_bu, "field 'buProductLayout'", LinearLayout.class);
        target.ekProductLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C0676R.id.product_ek, "field 'ekProductLayout'", LinearLayout.class);
        target.saccProductLayout = Utils.findRequiredView(source, C0676R.id.product_sacc, "field 'saccProductLayout'");
        target.srtProductLayout = Utils.findRequiredView(source, C0676R.id.product_srt, "field 'srtProductLayout'");
        target.ibProductLayout = Utils.findRequiredView(source, C0676R.id.product_ib, "field 'ibProductLayout'");
        target.mToolbar = (Toolbar) Utils.findRequiredViewAsType(source, C0676R.id.toolbar, "field 'mToolbar'", Toolbar.class);
    }

    public void unbind() {
        ChooseProductActivity target = (ChooseProductActivity) this.target;
        super.unbind();
        target.stProductLayout = null;
        target.buProductLayout = null;
        target.ekProductLayout = null;
        target.saccProductLayout = null;
        target.srtProductLayout = null;
        target.ibProductLayout = null;
        target.mToolbar = null;
    }
}
