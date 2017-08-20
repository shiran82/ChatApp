package com.chatapp.sp.module;

public class ChatItem {
    private long time;
    private boolean animate;
    private int type;

    public ChatItem(long time, int type, boolean animate) {
        this.time = time;
        this.type = type;
        this.animate = animate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isAnimate() {
        return animate;
    }

    public void setAnimate(boolean animate) {
        this.animate = animate;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
