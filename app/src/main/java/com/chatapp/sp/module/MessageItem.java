package com.chatapp.sp.module;

public class MessageItem extends ChatItem {

    public static final int TYPE_INCOMING_MESSAGE = 0;
    public static final int TYPE_OUTGOING_MESSAGE = 1;

    private String message;

    public MessageItem(String message, int type) {
        this.type = type;
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}