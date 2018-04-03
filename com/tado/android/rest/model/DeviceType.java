package com.tado.android.rest.model;

public enum DeviceType {
    UNKNOWN(99, 99, 99),
    TS01(4, 0, 3),
    TS02(4, 0, 3),
    BY01(4, 2, 1),
    BX01(4, 1, 1),
    BX02(4, 1, 1),
    BU01(4, 4, 3),
    RU01(4, 0, 1),
    RU02(4, 0, 1),
    WR01(4, 5, 2),
    VA01(4, 3, 0),
    GW01(3, 5, 3),
    GW02(2, 5, 3),
    GW03(1, 5, 3),
    IB01(0, 5, 3);
    
    private int priority;
    private int priorityList;
    private int uninstalledPriority;

    private DeviceType(int priority, int priorityList, int uninstalledPriority) {
        this.priority = priority;
        this.priorityList = priorityList;
        this.uninstalledPriority = uninstalledPriority;
    }

    public int getPriority() {
        return this.priority;
    }

    public int getPriorityList() {
        return this.priorityList;
    }

    public int getUninstalledPriority() {
        return this.uninstalledPriority;
    }
}
