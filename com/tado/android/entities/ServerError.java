package com.tado.android.entities;

public class ServerError {
    private String code;
    private InstallationStatus installationProcess;
    private String title;

    public ServerError(String code, InstallationStatus installationProcess, String title) {
        this.code = code;
        this.installationProcess = installationProcess;
        this.title = title;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public InstallationStatus getInstallationProcess() {
        return this.installationProcess;
    }

    public void setInstallationProcess(InstallationStatus installationProcess) {
        this.installationProcess = installationProcess;
    }
}
