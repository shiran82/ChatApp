package com.chatapp.sp.module;

public class MessageItem extends ChatItem {

    public static final int TYPE_INCOMING_MESSAGE = 100;
    public static final int TYPE_OUTGOING_MESSAGE = 200;

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