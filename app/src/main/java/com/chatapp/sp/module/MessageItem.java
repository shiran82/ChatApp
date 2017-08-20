package com.chatapp.sp.module;

public class MessageItem extends ChatItem {
    private String message;

    public MessageItem(long timestamp, int type, boolean animate, String message) {
        super(timestamp, type, animate);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}