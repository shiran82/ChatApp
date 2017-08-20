package com.chatapp.sp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.chatapp.sp.Constant;
import com.chatapp.sp.R;
import com.chatapp.sp.adapter.viewholder.ChatLineViewHolder;
import com.chatapp.sp.adapter.viewholder.MessageViewHolder;
import com.chatapp.sp.adapter.viewholder.TimestampViewHolder;
import com.chatapp.sp.databinding.ItemMessageBinding;
import com.chatapp.sp.databinding.ItemTimestampBinding;
import com.chatapp.sp.module.ChatItem;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatLineViewHolder> {
    private List<ChatItem> chatItems;
    private Context context;

    public ChatAdapter(Context context, List<ChatItem> chatItems) {
        this.chatItems = chatItems;
        this.context = context;
    }

    @Override
    public ChatLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ChatLineViewHolder viewHolder = null;

        switch (viewType) {
            case Constant.TYPE_INCOMING_MESSAGE:
            case Constant.TYPE_OUTGOING_MESSAGE:
                ItemMessageBinding itemMessageBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.item_message, parent, false);
                viewHolder = new MessageViewHolder(itemMessageBinding.getRoot());
                break;
            case Constant.TYPE_TIMESTAMP:
                ItemTimestampBinding itemTimestampBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.item_timestamp, parent, false);
                viewHolder = new TimestampViewHolder(itemTimestampBinding.getRoot());
                break;
            default:
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ChatLineViewHolder chatLineViewHolder, int position) {
        chatLineViewHolder.init(chatItems.get(position), context, chatLineViewHolder.itemView, position == 0 ?
            Constant.TYPE_UNKNOWN : chatItems.get(position - 1).getType());
    }

    @Override
    public int getItemViewType(int position) {
        return chatItems.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return chatItems.size();
    }

    public ChatItem getItem(int position) {
        return chatItems.get(position);
    }

    public void add(ChatItem messageItem) {
        chatItems.add(messageItem);
    }
}
