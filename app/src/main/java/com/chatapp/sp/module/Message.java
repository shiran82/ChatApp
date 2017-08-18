package com.chatapp.sp.module;

public class Message {

    public static final int TYPE_INCOMING_MESSAGE = 0;
    public static final int TYPE_OUTGOING_MESSAGE = 1;

    private int type;
    private String message;

    private Message() {
    }

    public Message(String message, int type) {
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