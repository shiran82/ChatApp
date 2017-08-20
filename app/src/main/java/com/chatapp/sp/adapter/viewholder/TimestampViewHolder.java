package com.chatapp.sp.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.chatapp.sp.R;
import com.chatapp.sp.module.ChatItem;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampViewHolder extends ChatLineViewHolder {
    private TextView textViewMessage;

    public TimestampViewHolder(View itemView) {
        super(itemView);

        textViewMessage = itemView.findViewById(R.id.text_view_time_stamp);
    }

    @Override
    public void init(ChatItem chatItem, Context context, View view, int lastItemType) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        textViewMessage.setText(sdf.format(new Date(chatItem.getTime())));
    }


    public void setTimestamp(String timestamp) {
        textViewMessage.setText(timestamp);
    }
}