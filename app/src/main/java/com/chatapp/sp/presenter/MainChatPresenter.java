package com.chatapp.sp.presenter;

import com.chatapp.sp.Constant;
import com.chatapp.sp.module.MessageItem;
import com.chatapp.sp.repository.ChatAppRepository;
import com.chatapp.sp.screen.MainChatMvpView;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URISyntaxException;

import io.socket.client.Socket;

public class MainChatPresenter {
    private ChatAppRepository repository;
    private MainChatMvpView mvpView;
    private static String EVENT_NEW_MESSAGE = "new message";

    public MainChatPresenter(ChatAppRepository repository, MainChatMvpView mvpView) {
        this.repository = repository;
        this.mvpView = mvpView;
    }

    public void setListenersAndConnect() {
        try {
            repository.openSocket();
        } catch (URISyntaxException e) {
            mvpView.showOnConnectError();
            return;
        }

        repository.addEventListener(Socket.EVENT_DISCONNECT, event -> mvpView.showOnDisconnected());
        repository.addEventListener(Socket.EVENT_CONNECT_TIMEOUT, event -> mvpView.showOnConnectError());
        repository.addEventListener(Socket.EVENT_CONNECTING, event -> mvpView.showOnConnect());
        repository.addEventListener(Socket.EVENT_CONNECT_ERROR, event -> mvpView.showOnConnectError());
        repository.addEventListener(EVENT_NEW_MESSAGE, event -> {
            JsonParser jsonParser = new JsonParser();
            JsonObject gsonObject = (JsonObject) jsonParser.parse(event[0].toString());
            MessageItem messageItem = new GsonBuilder().create().fromJson(gsonObject, MessageItem.class);
            messageItem.setTime(System.currentTimeMillis());//this should be determined by server
            timestampDecider(mvpView.getTimeBetweenLastAndNewItem(messageItem.getTime()));
            mvpView.showIncomingMessage(messageItem.getMessage(), messageItem.getTime());
        });

        repository.connect();
    }

    public void onDetach() {
        repository.removeEventListeners();
        repository.disconnect();
    }

    public void sendMessage(String message) {
        if (message == null || message.trim().length() == 0) {
            return;
        }
        if (repository.isConnected()) {
            repository.sendMessage(message);
            long newItemTime = System.currentTimeMillis();
            timestampDecider(mvpView.getTimeBetweenLastAndNewItem(newItemTime));
            mvpView.showOutgoingMessage(true, newItemTime);
            mvpView.showIncomingMessage(getMockAnswer(message).getMessage(), newItemTime);
        } else {
            mvpView.showOnConnectError();
        }
    }

    public void timestampDecider(long differenceBetweenItems) {
        if (differenceBetweenItems > Constant.INTERVAL_TO_SHOW_TIMESTAMP || differenceBetweenItems == 0) {
            mvpView.showTimestamp();
        }
    }

    public MessageItem getMockAnswer(String message) {
        MessageItem item = new MessageItem(System.currentTimeMillis(), Constant.TYPE_INCOMING_MESSAGE, true,
            message);

        return item;
    }

    public void requestItemsFromLocalDB() {
        //will use realm to save and fetch items from local db and call the relevant show message method in the activity
    }
}
