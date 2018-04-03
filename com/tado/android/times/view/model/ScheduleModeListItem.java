package com.tado.android.times.view.model;

import com.tado.android.rest.model.ScheduleMode;

public class ScheduleModeListItem {
    private ScheduleMode mScheduleMode;
    private boolean mSelected = false;
    private String mTitle;
    private ScheduleModeListItemType mType;

    public enum ScheduleModeListItemType {
        TITLE,
        ITEM
    }

    public ScheduleModeListItem(ScheduleMode scheduleMode, ScheduleModeListItemType type, String title) {
        this.mScheduleMode = scheduleMode;
        this.mType = type;
        this.mTitle = title;
    }

    public ScheduleModeListItemType getType() {
        return this.mType;
    }

    public void setType(ScheduleModeListItemType type) {
        this.mType = type;
    }

    public ScheduleMode getScheduleMode() {
        return this.mScheduleMode;
    }

    public void setScheduleMode(ScheduleMode scheduleMode) {
        this.mScheduleMode = scheduleMode;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public boolean isSelected() {
        return this.mSelected;
    }

    public void setSelected(boolean selected) {
        this.mSelected = selected;
    }
}
