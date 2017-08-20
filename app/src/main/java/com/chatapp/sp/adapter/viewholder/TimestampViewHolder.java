package com.chatapp.sp.adapter.viewholder;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.chatapp.sp.databinding.ItemTimestampBinding;
import com.chatapp.sp.module.ChatItem;
import com.chatapp.sp.tool.TimeTool;

import java.util.Date;

public class TimestampViewHolder extends ChatLineViewHolder {
    private ItemTimestampBinding binding;

    public TimestampViewHolder(View itemView) {
        super(itemView);

        binding = DataBindingUtil.getBinding(itemView);
    }

    @Override
    public void init(ChatItem chatItem, Context context, View view, int lastItemType) {
        binding.textViewTimestamp.setText(TimeTool.getFormattedTime(new Date(chatItem.getTime())));
    }

    public void setTimestamp(String timestamp) {
        binding.textViewTimestamp.setText(timestamp);
    }
}