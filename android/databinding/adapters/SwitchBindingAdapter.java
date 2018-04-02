package android.databinding.adapters;

import android.annotation.TargetApi;
import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.widget.Switch;

@TargetApi(14)
@BindingMethods({@BindingMethod(attribute = "android:thumb", method = "setThumbDrawable", type = Switch.class), @BindingMethod(attribute = "android:track", method = "setTrackDrawable", type = Switch.class)})
public class SwitchBindingAdapter {
    @BindingAdapter({"android:switchTextAppearance"})
    public static void setSwitchTextAppearance(Switch view, int value) {
        view.setSwitchTextAppearance(null, value);
    }
}
