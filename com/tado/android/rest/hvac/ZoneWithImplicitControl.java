package com.tado.android.rest.model.hvac;

import com.google.gson.annotations.SerializedName;
import com.tado.android.rest.model.Zone.TypeEnum;
import java.util.ArrayList;
import java.util.List;

public class ZoneWithImplicitControl {
    @SerializedName("devices")
    private List<SrtDevice> devices = new ArrayList(1);
    @SerializedName("name")
    private String name;
    @SerializedName("type")
    private ZoneCreateType type = ZoneCreateType.IMPLICIT_CONTROL;
    @SerializedName("zoneType")
    private TypeEnum zoneType = TypeEnum.HEATING;

    private enum ZoneCreateType {
        IMPLICIT_CONTROL,
        EXPLICIT_CONTROL
    }

    public ZoneWithImplicitControl(String zoneName, String serialNumber) {
        this.name = zoneName;
        SrtDevice srtDevice = new SrtDevice();
        srtDevice.setSerialNo(serialNumber);
        this.devices.add(srtDevice);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDevices(List<SrtDevice> devices) {
        this.devices = devices;
    }

    public String getName() {
        return this.name;
    }
}
