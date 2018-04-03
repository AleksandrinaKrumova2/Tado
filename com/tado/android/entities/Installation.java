package com.tado.android.entities;

public class Installation {
    private Device[] devices;
    private ServerError[] errors;
    private InstallationStatus installationProcess;
    private Manufacturer manufacturer;
    private ACSettingCommandSetRecording recording;

    public ACSettingCommandSetRecording getRecording() {
        return this.recording;
    }

    public void setRecording(ACSettingCommandSetRecording recording) {
        this.recording = recording;
    }

    public Installation(InstallationStatus installationProcess, Device[] devices) {
        this.installationProcess = installationProcess;
        this.devices = devices;
    }

    public InstallationStatus getInstallationProcess() {
        return this.installationProcess;
    }

    public void setInstallationProcess(InstallationStatus installationProcess) {
        this.installationProcess = installationProcess;
    }

    public Device[] getDevices() {
        return this.devices;
    }

    public void setDevices(Device[] devices) {
        this.devices = devices;
    }

    public ServerError[] getErrors() {
        return this.errors;
    }

    public void setErrors(ServerError[] errors) {
        this.errors = errors;
    }

    public Manufacturer getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
