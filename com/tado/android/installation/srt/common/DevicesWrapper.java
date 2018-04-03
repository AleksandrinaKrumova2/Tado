package com.tado.android.installation.srt.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.tado.android.rest.model.DeviceType;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DevicesWrapper {
    List<GenericHardwareDevice> devices = new ArrayList();

    class C09511 implements Comparator<GenericHardwareDevice> {
        C09511() {
        }

        public int compare(GenericHardwareDevice ld, GenericHardwareDevice rd) {
            return ld.getDeviceType().getPriority() - rd.getDeviceType().getPriority();
        }
    }

    public DevicesWrapper(@NonNull List<GenericHardwareDevice> devices) {
        if (devices != null) {
            this.devices = devices;
            sortGatewayDevices(this.devices);
        }
    }

    private void sortGatewayDevices(List<GenericHardwareDevice> devices) {
        Collections.sort(devices, new C09511());
    }

    @Nullable
    public GenericHardwareDevice getFirstNonGW01GatewayDevice() {
        for (GenericHardwareDevice device : this.devices) {
            if (device.isGateway() && device.getDeviceType() != DeviceType.GW01) {
                return device;
            }
        }
        return null;
    }
}
