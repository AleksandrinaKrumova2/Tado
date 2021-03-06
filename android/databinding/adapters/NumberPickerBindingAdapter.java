package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;

@InverseBindingMethods({@InverseBindingMethod(attribute = "android:value", type = NumberPicker.class)})
@BindingMethods({@BindingMethod(attribute = "android:format", method = "setFormatter", type = NumberPicker.class), @BindingMethod(attribute = "android:onScrollStateChange", method = "setOnScrollListener", type = NumberPicker.class)})
public class NumberPickerBindingAdapter {
    @BindingAdapter({"android:value"})
    public static void setValue(NumberPicker view, int value) {
        if (view.getValue() != value) {
            view.setValue(value);
        }
    }

    @BindingAdapter(requireAll = false, value = {"android:onValueChange", "android:valueAttrChanged"})
    public static void setListeners(NumberPicker view, final OnValueChangeListener listener, final InverseBindingListener attrChange) {
        if (attrChange == null) {
            view.setOnValueChangedListener(listener);
        } else {
            view.setOnValueChangedListener(new OnValueChangeListener() {
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    if (listener != null) {
                        listener.onValueChange(picker, oldVal, newVal);
                    }
                    attrChange.onChange();
                }
            });
        }
    }
}
