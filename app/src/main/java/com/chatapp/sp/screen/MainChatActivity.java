package com.chatapp.sp.screen;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.chatapp.sp.Constant;
import com.chatapp.sp.R;
import com.chatapp.sp.adapter.ChatAdapter;
import com.chatapp.sp.databinding.ActivityMainChatBinding;
import com.chatapp.sp.module.ChatItem;
import com.chatapp.sp.module.MessageItem;
import com.chatapp.sp.module.TimestampItem;
import com.chatapp.sp.presenter.MainChatPresenter;
import com.chatapp.sp.repository.ChatAppDataRepository;

import java.util.ArrayList;

public class MainChatActivity extends Activity implements MainChatMvpView {
    private ActivityMainChatBinding binding;
    private ChatAdapter chatAdapter;
    private MainChatPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_chat);
        presenter = new MainChatPresenter(new ChatAppDataRepository(), this);

        presenter.setListenersAndConnect();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatAdapter = new ChatAdapter(this, new ArrayList<>());
        binding.recyclerView.setAdapter(chatAdapter);
        binding.buttonSendMessage.setOnClickListener(view -> {
            presenter.sendMessage(binding.editTextMessage.getText().toString());
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    public void showOnDisconnected() {
        runOnUiThread(() -> {
            Toast.makeText(getApplicationContext(),
                getString(R.string.disconnected), Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void showOnConnect() {
        runOnUiThread(() -> Toast.makeText(getApplicationContext(),
            getString(R.string.connected), Toast.LENGTH_LONG).show());
    }

    @Override
    public void showOnConnectError() {
        runOnUiThread(() -> Toast.makeText(getApplicationContext(),
            getString(R.string.error_connecting), Toast.LENGTH_LONG).show());
    }

    @Override
    public void showIncomingMessage(String message, long timestamp) {
        runOnUiThread(() -> addMessage(message, Constant.TYPE_INCOMING_MESSAGE, true, timestamp));
    }

    @Override
    public void showOutgoingMessage(boolean animate, long timestamp) {
        addMessage(binding.editTextMessage.getText().toString(), Constant.TYPE_OUTGOING_MESSAGE, animate, timestamp);
        binding.editTextMessage.setText("");
    }

    @Override
    public void showTimestamp() {
        chatAdapter.add(new TimestampItem(System.currentTimeMillis(), Constant.TYPE_TIMESTAMP, false));
        runOnUiThread(() -> {
            chatAdapter.notifyItemInserted(chatAdapter.getItemCount() - 1);
            scrollToBottom();
        });
    }

    @Override
    public long getTimeBetweenLastAndNewItem(long newItemTime) {
        ChatItem lastItem = chatAdapter.getItemCount() - 1 < 0 ? null
            : chatAdapter.getItem(chatAdapter.getItemCount() - 1);
        return newItemTime - (lastItem == null ? newItemTime : lastItem.getTime());
    }

    private void addMessage(String message, int type, boolean animate, long timestamp) {
        chatAdapter.add(new MessageItem(timestamp, type, animate, message));
        chatAdapter.notifyItemInserted(chatAdapter.getItemCount() - 1);
        scrollToBottom();
    }

    private void scrollToBottom() {
        if (chatAdapter.getItemCount() > 0) {
            binding.recyclerView.scrollToPosition(chatAdapter.getItemCount() - 1);
        }
    }
}
