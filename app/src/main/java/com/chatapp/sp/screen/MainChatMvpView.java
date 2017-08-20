package com.chatapp.sp.screen;


import org.json.JSONObject;

public interface MainChatMvpView {

    void showOnDisconnected();

    void showOnConnect();

    void showOnConnectError();

    void showIncomingMessage(JSONObject data);

    void showOutgoingMessage(boolean animate);

    void showTimestamp();
}
