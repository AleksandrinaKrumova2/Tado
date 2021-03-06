package android.databinding.adapters;

import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.widget.ProgressBar;

@BindingMethods({@BindingMethod(attribute = "android:indeterminateTint", method = "setIndeterminateTintList", type = ProgressBar.class), @BindingMethod(attribute = "android:progressTint", method = "setProgressTintList", type = ProgressBar.class), @BindingMethod(attribute = "android:secondaryProgressTint", method = "setSecondaryProgressTintList", type = ProgressBar.class)})
public class ProgressBarBindingAdapter {
}
