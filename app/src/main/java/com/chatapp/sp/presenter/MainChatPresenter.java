package com.chatapp.sp.presenter;

import com.chatapp.sp.module.ChatItem;
import com.chatapp.sp.repository.ChatAppRepository;
import com.chatapp.sp.screen.MainChatMvpView;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.Socket;

public class MainChatPresenter {
    private ChatAppRepository repository;
    private MainChatMvpView mvpView;

    public MainChatPresenter(ChatAppRepository repository, MainChatMvpView mvpView) {
        this.repository = repository;
        this.mvpView = mvpView;

        repository.addEventListener(Socket.EVENT_DISCONNECT, event -> mvpView.showOnDisconnected());
        repository.addEventListener(Socket.EVENT_CONNECT_TIMEOUT, event -> mvpView.showOnConnectError());
        repository.addEventListener(Socket.EVENT_CONNECTING, event -> mvpView.showOnConnect());
        repository.addEventListener(Socket.EVENT_CONNECT_ERROR, event -> mvpView.showOnConnectError());
        repository.addEventListener("new message", event -> mvpView.showIncomingMessage((JSONObject) event[0]));
        repository.connect();
    }

    public void onDetach() {
        repository.removeEventListeners();
        repository.disconnect();
    }

    public void sendMessage(String message, ChatItem lastChatItem) {
        if (message == null || message.trim().length() == 0) {
            return;
        }
        if (repository.isConnected()) {
            repository.sendMessage(message);
            timestampDecider(lastChatItem);
            mvpView.showOutgoingMessage(true);
            JSONObject item = new JSONObject();
            try {
                item.put("message", message);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mvpView.showIncomingMessage(item);
        } else {
            mvpView.showOnConnectError();
        }
    }

    public void timestampDecider(ChatItem lastChatItem) {
        if (lastChatItem == null || System.currentTimeMillis() - lastChatItem.getTime() > 600000) {
            mvpView.showTimestamp();
        }
    }
}
