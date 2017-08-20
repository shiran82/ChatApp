package com.chatapp.sp.screen;


public interface MainChatMvpView {

    void showOnDisconnected();

    void showOnConnect();

    void showOnConnectError();

    void showIncomingMessage(String message, long timestamp);

    void showOutgoingMessage(boolean animate, long timestamp);

    void showTimestamp();

    long getTimeBetweenLastAndNewItem(long newItemTime);
}
