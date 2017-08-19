package com.chatapp.sp.screen;


import org.json.JSONObject;

public interface MainChatMvpView {
    void showIncomingMessage();

    void showOutgoingMessage();

    void showDisconnected();

    void showOnConnect();

    void showOnConnectError();

    void showOnNewMessage(JSONObject data);

    void showMessage();
}
