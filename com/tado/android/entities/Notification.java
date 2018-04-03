package com.tado.android.entities;

public class Notification {
    private int id;
    private String shortText;
    private String text;
    private String timestamp;
    private String type;

    public Notification(int id, String timestamp, String type, String text) {
        this.id = id;
        this.timestamp = timestamp;
        this.type = type;
        this.text = text;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean equals(Object o) {
        if ((o instanceof Notification) && hashCode() == o.hashCode() && ((Notification) o).getId() == this.id) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.id + 17;
    }

    public String getShortText() {
        return this.shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }
}
