package com.chatapp.sp.screen;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.chatapp.sp.R;
import com.chatapp.sp.adapter.ChatAdapter;
import com.chatapp.sp.databinding.ActivityMainChatBinding;
import com.chatapp.sp.module.MessageItem;
import com.chatapp.sp.module.TimestampItem;
import com.chatapp.sp.presenter.MainChatPresenter;
import com.chatapp.sp.repository.ChatAppDataRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MainChatActivity extends Activity implements MainChatMvpView {
    private ActivityMainChatBinding binding;
    private ChatAdapter chatAdapter;
    private Boolean isConnected = true;
    private MainChatPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_chat);
        presenter = new MainChatPresenter(new ChatAppDataRepository(), this);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatAdapter = new ChatAdapter(this, new ArrayList<>());
        binding.recyclerView.setAdapter(chatAdapter);
        binding.buttonSendMessage.setOnClickListener(view -> {
            presenter.sendMessage(binding.editTextMessage.getText().toString(), chatAdapter.getItem(chatAdapter
                .getItemCount() - 1));
        });
    }

    private void addMessage(String message, int type, boolean animate) {
        chatAdapter.add(new MessageItem(System.currentTimeMillis(), type, animate, message));
        chatAdapter.notifyItemInserted(chatAdapter.getItemCount() - 1);
        scrollToBottom();
    }

    private void scrollToBottom() {
        if (chatAdapter.getItemCount() > 0) {
            binding.recyclerView.scrollToPosition(chatAdapter.getItemCount() - 1);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    public void showDisconnected() {
        runOnUiThread(() -> {
            Log.i(TAG, "diconnected");
            isConnected = false;
            Toast.makeText(getApplicationContext(),
                "disconnected", Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void showOnConnect() {
        runOnUiThread(() -> {
            if (!isConnected) {
                Toast.makeText(getApplicationContext(),
                    "connected", Toast.LENGTH_LONG).show();
                isConnected = true;
            }
        });
    }

    @Override
    public void showOnConnectError() {
        runOnUiThread(() -> {
            Log.e(TAG, "Error connecting");
            Toast.makeText(getApplicationContext(),
                "error connecting", Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void showIncomingMessage(JSONObject data) {
        runOnUiThread(() -> {
            String message;
            try {
                presenter.showTimestampDecider(chatAdapter.getItem(chatAdapter.getItemCount() - 1));
                message = data.getString("message");
                Log.e(TAG, message);
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
                return;
            }

            addMessage(message, MessageItem.TYPE_INCOMING_MESSAGE, true);
        });
    }

    @Override
    public void showOutgoingMessage(boolean animate) {
        addMessage(binding.editTextMessage.getText().toString(), MessageItem.TYPE_OUTGOING_MESSAGE, animate);
        binding.editTextMessage.setText("");
    }

    @Override
    public void showTimestamp() {
        chatAdapter.add(new TimestampItem(System.currentTimeMillis(), TimestampItem.TYPE_TIMESTAMP, false));
        chatAdapter.notifyItemInserted(chatAdapter.getItemCount() - 1);
        scrollToBottom();
    }
}
