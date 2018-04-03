package com.tado.android.installation.teaching;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity_ViewBinding;

public class SetACToSettingActivity_ViewBinding<T extends SetACToSettingActivity> extends ACInstallationBaseActivity_ViewBinding<T> {
    @UiThread
    public SetACToSettingActivity_ViewBinding(T target, View source) {
        super(target, source);
        target.modeImageView = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.mode_icon_imageview, "field 'modeImageView'", ImageView.class);
        target.modeTextView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.mode_textview, "field 'modeTextView'", TextView.class);
        target.tempImageView = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.temperature_icon_imageview, "field 'tempImageView'", ImageView.class);
        target.tempTextView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.temperature_textview, "field 'tempTextView'", TextView.class);
        target.fanIconImageView = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.fan_speed_icon_imageview, "field 'fanIconImageView'", ImageView.class);
        target.fanTextView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.fan_speed_auto_textview, "field 'fanTextView'", TextView.class);
        target.swingImageView = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.swing_icon_imageview, "field 'swingImageView'", ImageView.class);
        target.swingTextView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.swing_textview, "field 'swingTextView'", TextView.class);
        target.acModeLayout = Utils.findRequiredView(source, C0676R.id.ac_mode_mode_layout, "field 'acModeLayout'");
        target.acModeTempLayout = Utils.findRequiredView(source, C0676R.id.ac_mode_temp_layout, "field 'acModeTempLayout'");
        target.acModeFanLayout = Utils.findRequiredView(source, C0676R.id.ac_mode_fan_layout, "field 'acModeFanLayout'");
        target.acModeSwingLayout = Utils.findRequiredView(source, C0676R.id.ac_mode_swing_layout, "field 'acModeSwingLayout'");
    }

    public void unbind() {
        SetACToSettingActivity target = (SetACToSettingActivity) this.target;
        super.unbind();
        target.modeImageView = null;
        target.modeTextView = null;
        target.tempImageView = null;
        target.tempTextView = null;
        target.fanIconImageView = null;
        target.fanTextView = null;
        target.swingImageView = null;
        target.swingTextView = null;
        target.acModeLayout = null;
        target.acModeTempLayout = null;
        target.acModeFanLayout = null;
        target.acModeSwingLayout = null;
    }
}
