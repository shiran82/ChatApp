package com.chatapp.sp.module;

public class MessageItem extends ChatItem {

    public static final int TYPE_INCOMING_MESSAGE = 0;
    public static final int TYPE_OUTGOING_MESSAGE = 1;

    private String message;

    public MessageItem(String message, int type, boolean animate) {
        this.type = type;
        this.message = message;
        this.animate = animate;
    }

    public int getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}