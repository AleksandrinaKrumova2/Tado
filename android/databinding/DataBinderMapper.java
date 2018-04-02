package android.databinding;

import android.view.View;
import com.tado.C0676R;
import com.tado.databinding.ActivityDeviceDetailsBinding;

class DataBinderMapper {
    static final int TARGET_MIN_SDK = 15;

    private static class InnerBrLookup {
        static String[] sKeys = new String[]{"_all", "device", "zoneType"};

        private InnerBrLookup() {
        }
    }

    public ViewDataBinding getDataBinder(DataBindingComponent bindingComponent, View view, int layoutId) {
        switch (layoutId) {
            case C0676R.layout.activity_device_details:
                return ActivityDeviceDetailsBinding.bind(view, bindingComponent);
            default:
                return null;
        }
    }

    ViewDataBinding getDataBinder(DataBindingComponent bindingComponent, View[] views, int layoutId) {
        return null;
    }

    int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        switch (tag.hashCode()) {
            case -1805621419:
                if (tag.equals("layout/activity_device_details_0")) {
                    return C0676R.layout.activity_device_details;
                }
                return 0;
            default:
                return 0;
        }
    }

    String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
}
