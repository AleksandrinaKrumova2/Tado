package com.tado.android.control_panel;

public class OverlayTerminationListItem {
    private boolean mEnabled;
    private String mName;
    private int mResourceId;
    private boolean mSelected;
    private String mType;

    public OverlayTerminationListItem(String name, int resourceId, boolean selected, String type) {
        this(name, resourceId, selected, type, true);
    }

    public OverlayTerminationListItem(String name, int resourceId, boolean selected, String type, boolean enabled) {
        this.mName = name;
        this.mResourceId = resourceId;
        this.mSelected = selected;
        this.mType = type;
        this.mEnabled = enabled;
    }

    public boolean isSelected() {
        return this.mSelected;
    }

    public void setSelected(boolean selected) {
        this.mSelected = selected;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public int getResourceId() {
        return this.mResourceId;
    }

    public void setResourceId(int resourceId) {
        this.mResourceId = resourceId;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }
}
