package com.chatapp.sp.presenter;

import com.chatapp.sp.repository.ChatAppRepository;
import com.chatapp.sp.screen.MainChatMvpView;

import org.json.JSONObject;

import io.socket.client.Socket;

public class MainChatPresenter {
    private ChatAppRepository repository;
    private MainChatMvpView mvpView;

    public MainChatPresenter(ChatAppRepository repository, MainChatMvpView mvpView) {
        this.repository = repository;
        this.mvpView = mvpView;

        repository.addEventListener(Socket.EVENT_DISCONNECT, event -> mvpView.showDisconnected());
        repository.addEventListener(Socket.EVENT_CONNECT_TIMEOUT, event -> mvpView.showOnConnectError());
        repository.addEventListener(Socket.EVENT_CONNECTING, event -> mvpView.showOnConnect());
        repository.addEventListener(Socket.EVENT_CONNECT_ERROR, event -> mvpView.showOnConnectError());
        repository.addEventListener("new message", event -> mvpView.showOnNewMessage((JSONObject) event[0]));
        repository.connect();
    }

    public void sendMessage(String message) {
        if (message == null || message.trim().length() == 0) {
            return;
        }
        if (repository.isConnected()) {
            repository.sendMessage(message);
            mvpView.showMessage();
        } else {
            mvpView.showOnConnectError();
        }
    }

    public void onDetach() {
        repository.removeEventListeners();
        repository.disconnect();
    }
}
