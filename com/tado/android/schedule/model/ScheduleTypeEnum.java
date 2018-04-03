package com.tado.android.schedule.model;

public enum ScheduleTypeEnum {
    ONEDAY(0, "ONE_DAY"),
    THREEDAY(1, "THREE_DAY"),
    SEVENDAY(2, "SEVEN_DAY");
    
    private int mId;
    private String mType;

    private ScheduleTypeEnum(int id, String mType) {
        this.mId = id;
        this.mType = mType;
    }

    public String getType() {
        return this.mType;
    }

    public int getId() {
        return this.mId;
    }

    public static ScheduleTypeEnum getScheduleType(int type) {
        switch (type) {
            case 0:
                return ONEDAY;
            case 1:
                return THREEDAY;
            default:
                return SEVENDAY;
        }
    }

    public static ScheduleTypeEnum getScheduleType(String type) {
        if (ONEDAY.mType.equals(type)) {
            return ONEDAY;
        }
        if (THREEDAY.mType.equals(type)) {
            return THREEDAY;
        }
        if (SEVENDAY.mType.equals(type)) {
            return SEVENDAY;
        }
        return ONEDAY;
    }
}
