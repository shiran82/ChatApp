package com.chatapp.sp.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.chatapp.sp.R;
import com.chatapp.sp.module.ChatItem;
import com.chatapp.sp.module.TimestampItem;

public class TimestampViewHolder extends ChatLineViewHolder {
    private TextView textViewMessage;

    public TimestampViewHolder(View itemView) {
        super(itemView);

        textViewMessage = itemView.findViewById(R.id.text_view_time_stamp);
    }

    @Override
    public void init(ChatItem chatItem, Context context, View view) {
        setTimestamp(((TimestampItem) chatItem).getTimestamp());
    }


    public void setTimestamp(String timestamp) {
        textViewMessage.setText("22/3/2017");
    }
}