package com.tado.android.installation.complexteaching;

import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity_ViewBinding;

public class TeachingModesOverviewActivity_ViewBinding<T extends TeachingModesOverviewActivity> extends ACInstallationBaseActivity_ViewBinding<T> {
    @UiThread
    public TeachingModesOverviewActivity_ViewBinding(T target, View source) {
        super(target, source);
        target.coolModeButton = (FloatingActionButton) Utils.findRequiredViewAsType(source, C0676R.id.control_first_button, "field 'coolModeButton'", FloatingActionButton.class);
        target.coolTick = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.tick_first_button, "field 'coolTick'", ImageView.class);
        target.coolModeTextView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.control_first_button_text, "field 'coolModeTextView'", TextView.class);
        target.coolModeLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C0676R.id.control_first_button_layout, "field 'coolModeLayout'", LinearLayout.class);
        target.dryModeButton = (FloatingActionButton) Utils.findRequiredViewAsType(source, C0676R.id.control_second_button, "field 'dryModeButton'", FloatingActionButton.class);
        target.dryTick = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.tick_second_button, "field 'dryTick'", ImageView.class);
        target.dryModeTextView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.control_second_button_text, "field 'dryModeTextView'", TextView.class);
        target.dryModeLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C0676R.id.control_second_button_layout, "field 'dryModeLayout'", LinearLayout.class);
        target.fanModeButton = (FloatingActionButton) Utils.findRequiredViewAsType(source, C0676R.id.control_third_button, "field 'fanModeButton'", FloatingActionButton.class);
        target.fanTick = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.tick_third_button, "field 'fanTick'", ImageView.class);
        target.fanModeTextView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.control_third_button_text, "field 'fanModeTextView'", TextView.class);
        target.fanModeLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C0676R.id.control_third_button_layout, "field 'fanModeLayout'", LinearLayout.class);
        target.autoModeButton = (FloatingActionButton) Utils.findRequiredViewAsType(source, C0676R.id.control_fourth_button, "field 'autoModeButton'", FloatingActionButton.class);
        target.autoTick = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.tick_fourth_button, "field 'autoTick'", ImageView.class);
        target.autoModeTextView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.control_fourth_button_text, "field 'autoModeTextView'", TextView.class);
        target.autoModeLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C0676R.id.control_fourth_button_layout, "field 'autoModeLayout'", LinearLayout.class);
        target.heatModeButton = (FloatingActionButton) Utils.findRequiredViewAsType(source, C0676R.id.control_fifth_button, "field 'heatModeButton'", FloatingActionButton.class);
        target.heatTick = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.tick_fifth_button, "field 'heatTick'", ImageView.class);
        target.heatModeTextView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.control_fifth_button_text, "field 'heatModeTextView'", TextView.class);
        target.heatModeLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C0676R.id.control_fifth_button_layout, "field 'heatModeLayout'", LinearLayout.class);
        target.maybeLaterTextview = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.maybe_later_textview, "field 'maybeLaterTextview'", TextView.class);
    }

    public void unbind() {
        TeachingModesOverviewActivity target = (TeachingModesOverviewActivity) this.target;
        super.unbind();
        target.coolModeButton = null;
        target.coolTick = null;
        target.coolModeTextView = null;
        target.coolModeLayout = null;
        target.dryModeButton = null;
        target.dryTick = null;
        target.dryModeTextView = null;
        target.dryModeLayout = null;
        target.fanModeButton = null;
        target.fanTick = null;
        target.fanModeTextView = null;
        target.fanModeLayout = null;
        target.autoModeButton = null;
        target.autoTick = null;
        target.autoModeTextView = null;
        target.autoModeLayout = null;
        target.heatModeButton = null;
        target.heatTick = null;
        target.heatModeTextView = null;
        target.heatModeLayout = null;
        target.maybeLaterTextview = null;
    }
}
