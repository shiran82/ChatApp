package com.chatapp.sp.repository;


import java.net.URISyntaxException;

import io.socket.emitter.Emitter;

public interface ChatAppRepository {

    void openSocket() throws URISyntaxException;

    void connect();

    void addEventListener(String event, Emitter.Listener listener);

    void sendMessage(String message);

    void disconnect();

    void removeEventListeners();

    boolean isConnected();
}
