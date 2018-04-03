package com.tado.android.user_radar;

import com.tado.android.rest.model.MobileDevice;
import com.tado.android.utils.UserConfig;
import java.util.List;

public class RadarItemPosition {
    UserIconEnum icon;
    boolean isMe;
    String name;
    List<MobileDevice> users;
    int f413x;
    int f414y;

    public RadarItemPosition(String name, int mobileId, UserIconEnum icon, List<MobileDevice> users, int x, int y) {
        boolean z = false;
        this.name = name;
        this.icon = icon;
        this.users = users;
        this.f413x = x;
        this.f414y = y;
        if (UserConfig.getMobileDeviceId() != -1 && UserConfig.getMobileDeviceId() == mobileId) {
            z = true;
        }
        this.isMe = z;
    }

    public int getX() {
        return this.f413x;
    }

    public void setX(int x) {
        this.f413x = x;
    }

    public int getY() {
        return this.f414y;
    }

    public void setY(int y) {
        this.f414y = y;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserIconEnum getIcon() {
        return this.icon;
    }

    public void setIcon(UserIconEnum icon) {
        this.icon = icon;
    }

    public List<MobileDevice> getUsers() {
        return this.users;
    }

    public void setUsers(List<MobileDevice> users) {
        this.users = users;
    }

    public boolean isMe() {
        return this.isMe;
    }
}
