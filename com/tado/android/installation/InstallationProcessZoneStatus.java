package com.tado.android.installation;

public class InstallationProcessZoneStatus {
    private InstallationProcessZoneStatusEnum status;

    public enum InstallationProcessZoneStatusEnum {
        ZONE_INSTALLATION_COMPLETED,
        ZONE_INSTALLATION_NOT_COMPLETED,
        ZONE_INSTALLATION_DELETED,
        ZONE_INSTALLATION_ERROR
    }

    private InstallationProcessZoneStatus() {
    }

    public static InstallationProcessZoneStatus create() {
        return new InstallationProcessZoneStatus();
    }

    public InstallationProcessZoneStatus with(InstallationProcessZoneStatusEnum status) {
        this.status = status;
        return this;
    }

    public InstallationProcessZoneStatusEnum getStatus() {
        return this.status;
    }
}
