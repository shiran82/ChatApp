package com.chatapp.sp.repository;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class ChatAppDataRepository implements ChatAppRepository {
    private final String URI = "https://socketio-chat.now.sh/";
    private Socket socket;

    @Override
    public void openSocket() throws URISyntaxException {
        socket = IO.socket(URI);
    }

    @Override
    public void connect() {
        if (socket != null) {
            socket.connect();
        }
    }

    @Override
    public void addEventListener(String event, Emitter.Listener listener) {
        socket.on(event, listener);
    }

    @Override
    public void sendMessage(String message) {
        socket.emit("new message", message);
    }

    @Override
    public void disconnect() {

        if (socket != null) {
            socket.disconnect();
        }
    }

    @Override
    public void removeEventListeners() {
        if (socket != null) {
            socket.off();
        }
    }

    @Override
    public boolean isConnected() {
        return socket == null ? false : socket.connected();
    }
}
