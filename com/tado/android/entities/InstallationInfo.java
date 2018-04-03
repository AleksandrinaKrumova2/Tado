package com.tado.android.entities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.tado.android.rest.model.Zone;
import com.tado.android.rest.model.installation.AcInstallation;
import com.tado.android.rest.model.installation.AcInstallation.StateEnum;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import com.tado.android.rest.model.installation.Installation;
import com.tado.android.rest.model.installation.Installation.TypeEnum;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class InstallationInfo implements Serializable {
    private Installation currentInstallation;
    private GenericHardwareDevice currentUninstalledDevice;
    private List<GenericHardwareDevice> devices = new ArrayList();
    private List<Installation> installations = new ArrayList();
    private List<Installation> unfinishedInstallations;
    private List<GenericHardwareDevice> uninstalledDevices = new ArrayList();
    private List<Zone> zones = new ArrayList();

    class C07841 implements Comparator<GenericHardwareDevice> {
        C07841() {
        }

        public int compare(GenericHardwareDevice o1, GenericHardwareDevice o2) {
            return o1.getDeviceType().getUninstalledPriority() - o2.getDeviceType().getUninstalledPriority();
        }
    }

    public boolean isStartInstallation() {
        return this.zones != null && this.zones.isEmpty() && getUnfinishedInstallations() != null && getUnfinishedInstallations().isEmpty() && this.uninstalledDevices != null && this.uninstalledDevices.isEmpty();
    }

    public boolean hasUnfinishedInstallations() {
        return this.uninstalledDevices != null && this.uninstalledDevices.size() > 0;
    }

    @Nullable
    public Installation getUnfinishedInstallation(GenericHardwareDevice device) {
        for (Installation installation : getUnfinishedInstallations()) {
            for (GenericHardwareDevice installationDevice : installation.getDevices()) {
                if (installationDevice.getShortSerialNo().equalsIgnoreCase(device.getShortSerialNo())) {
                    return installation;
                }
            }
        }
        return null;
    }

    private void calculateUninstalledDevices(@NonNull List<GenericHardwareDevice> devices, List<Zone> zones, List<Installation> unfinishedInstallations) {
        this.uninstalledDevices = new ArrayList(devices.size());
        processInstallations(unfinishedInstallations);
        processZones(devices, zones);
        sortDevices(this.uninstalledDevices);
    }

    private void processZones(@NonNull List<GenericHardwareDevice> devices, List<Zone> zones) {
        if (zones != null) {
            for (GenericHardwareDevice device : devices) {
                if (device.isValve() && !isDeviceAssigned(zones, device)) {
                    this.uninstalledDevices.add(device);
                }
            }
        }
    }

    private void processInstallations(List<Installation> unfinishedInstallations) {
        if (unfinishedInstallations != null) {
            for (Installation installation : unfinishedInstallations) {
                if (installation != null) {
                    if (installation.getDevices().size() == 0 && TypeEnum.SALE_FITTING_ST_G1 == installation.getType()) {
                        this.uninstalledDevices.add(GenericHardwareDevice.generateSaleFittingDevice());
                    } else {
                        for (GenericHardwareDevice uninstalledDevice : installation.getDevices()) {
                            this.uninstalledDevices.add(uninstalledDevice);
                        }
                    }
                }
            }
        }
    }

    private boolean isDeviceAssigned(List<Zone> zones, GenericHardwareDevice device) {
        for (Zone zone : zones) {
            if (zone.isHeatingZone()) {
                for (GenericHardwareDevice zoneDevice : zone.getDevices()) {
                    if (zoneDevice.getShortSerialNo().equalsIgnoreCase(device.getShortSerialNo())) {
                        return true;
                    }
                }
                continue;
            }
        }
        return false;
    }

    public List<GenericHardwareDevice> getUninstalledDevices() {
        return this.uninstalledDevices;
    }

    private void sortDevices(List<GenericHardwareDevice> devices) {
        Collections.sort(devices, new C07841());
    }

    public boolean hasZones() {
        return this.zones != null && this.zones.size() > 0;
    }

    public void addUninstalledDevice(GenericHardwareDevice device) {
        this.uninstalledDevices.add(device);
        sortDevices(this.uninstalledDevices);
    }

    public void init(InstallationInfo installationInfo) {
        if (!(this.zones == null || this.zones == installationInfo.zones)) {
            this.zones.clear();
            if (installationInfo.zones != null) {
                for (Zone zone : installationInfo.zones) {
                    this.zones.add(zone);
                }
            }
        }
        if (!(this.installations == null || this.installations == installationInfo.installations)) {
            this.unfinishedInstallations = null;
            this.installations.clear();
            if (installationInfo.installations != null) {
                for (Installation installation : installationInfo.installations) {
                    this.installations.add(installation);
                }
            }
        }
        if (!(this.devices == null || this.devices == installationInfo.devices)) {
            this.devices.clear();
            if (installationInfo.devices != null) {
                for (GenericHardwareDevice device : installationInfo.devices) {
                    this.devices.add(device);
                }
            }
        }
        setCurrentInstallation(installationInfo.getCurrentInstallation());
        setCurrentUninstalledDevice(installationInfo.getCurrentUninstalledDevice());
        if (this.uninstalledDevices != null) {
            this.uninstalledDevices.clear();
        }
        calculateUninstalledDevices(this.devices, this.zones, getUnfinishedInstallations());
    }

    public void init(List<Zone> zones, List<Installation> installations, List<GenericHardwareDevice> devices) {
        this.zones = zones;
        this.installations = installations;
        this.devices = devices;
        init(this);
    }

    public void removeDeviceFromHome(GenericHardwareDevice removedDevice) {
        Iterator<GenericHardwareDevice> deviceIterator = this.devices.iterator();
        while (deviceIterator.hasNext()) {
            if (removedDevice.getShortSerialNo().equals(((GenericHardwareDevice) deviceIterator.next()).getShortSerialNo())) {
                deviceIterator.remove();
                break;
            }
        }
        deviceIterator = this.uninstalledDevices.iterator();
        while (deviceIterator.hasNext()) {
            if (removedDevice.getShortSerialNo().equals(((GenericHardwareDevice) deviceIterator.next()).getShortSerialNo())) {
                deviceIterator.remove();
                break;
            }
        }
        Iterator<Zone> zoneIterator = this.zones.iterator();
        boolean deviceRemoved = false;
        while (zoneIterator.hasNext()) {
            Zone zone = (Zone) zoneIterator.next();
            deviceIterator = zone.getDevices().iterator();
            while (deviceIterator.hasNext()) {
                if (removedDevice.getShortSerialNo().equals(((GenericHardwareDevice) deviceIterator.next()).getShortSerialNo())) {
                    deviceIterator.remove();
                    deviceRemoved = true;
                    break;
                }
            }
            if (zone.getDevices().size() == 0) {
                zoneIterator.remove();
                continue;
            }
            if (deviceRemoved) {
                return;
            }
        }
    }

    public List<Installation> getUnfinishedInstallations() {
        if (this.unfinishedInstallations == null) {
            this.unfinishedInstallations = getUnfinishedInstallationFromInstallationList(this.installations);
        }
        return this.unfinishedInstallations;
    }

    private List<Installation> getUnfinishedInstallationFromInstallationList(@NonNull List<Installation> installations) {
        List<Installation> resultList = new ArrayList();
        Collections.sort(installations);
        for (Installation installation : installations) {
            if (installation.isInstallationNotCompleted()) {
                resultList.add(installation);
            }
        }
        return resultList;
    }

    public List<Installation> getInstallations() {
        return this.installations;
    }

    public List<Zone> getZones() {
        return this.zones;
    }

    public List<GenericHardwareDevice> getDevices() {
        return this.devices;
    }

    public Installation getCurrentInstallation() {
        return this.currentInstallation;
    }

    public void setCurrentInstallation(Installation currentInstallation) {
        this.currentInstallation = currentInstallation;
    }

    public GenericHardwareDevice getCurrentUninstalledDevice() {
        return this.currentUninstalledDevice;
    }

    public void setCurrentUninstalledDevice(GenericHardwareDevice currentUninstalledDevice) {
        this.currentUninstalledDevice = currentUninstalledDevice;
    }

    public boolean isCLCRecording() {
        return this.currentInstallation != null && (this.currentInstallation instanceof AcInstallation) && ((AcInstallation) this.currentInstallation).getState() == StateEnum.SETUP_AC_CLC_RECORDING;
    }
}
