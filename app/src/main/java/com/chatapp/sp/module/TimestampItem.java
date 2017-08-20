package com.chatapp.sp.module;

public class TimestampItem extends ChatItem {
    private String timestamp;

    public TimestampItem(long timestamp, int type, boolean animate) {
        super(timestamp, type, animate);
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp() {
        this.timestamp = timestamp;
    }
}