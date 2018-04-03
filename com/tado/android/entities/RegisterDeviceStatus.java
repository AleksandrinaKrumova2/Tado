package com.tado.android.entities;

public class RegisterDeviceStatus {
    private Device[] devices;
    private ServerError[] errors;
    private InstallationStatus installationProcess;

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
}
