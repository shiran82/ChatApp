package com.chatapp.sp.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chatapp.sp.module.ChatItem;

public abstract class ChatLineViewHolder extends RecyclerView.ViewHolder {
    public ChatLineViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void init(ChatItem chatItem, Context context, View view);
}