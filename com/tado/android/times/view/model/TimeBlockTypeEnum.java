package com.tado.android.times.view.model;

import com.tado.android.utils.Constants;

public enum TimeBlockTypeEnum {
    HOME(Constants.HOME, 0),
    SLEEP(Constants.SLEEP, 1),
    AWAY(Constants.AWAY, 2);
    
    private int mPosition;
    private String mType;

    private TimeBlockTypeEnum(String type, int position) {
        this.mPosition = position;
        this.mType = type;
    }

    public String getType() {
        return this.mType;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public static TimeBlockTypeEnum getValue(String type) {
        if (type.equals(Constants.HOME)) {
            return HOME;
        }
        if (type.equals(Constants.SLEEP)) {
            return SLEEP;
        }
        return AWAY;
    }
}
