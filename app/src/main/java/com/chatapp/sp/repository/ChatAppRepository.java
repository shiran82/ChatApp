package com.chatapp.sp.repository;


import io.socket.emitter.Emitter;

public interface ChatAppRepository {

    void connect();

    void addEventListener(String event, Emitter.Listener listener);

    void sendMessage(String message);

    void disconnect();

    void removeEventListeners();

    boolean isConnected();
}
