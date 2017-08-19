package com.chatapp.sp.adapter.viewholder;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chatapp.sp.R;
import com.chatapp.sp.module.ChatItem;
import com.chatapp.sp.module.MessageItem;

public class MessageViewHolder extends ChatLineViewHolder {
    private TextView textViewMessage;
    private int lastPosition = -1;
    private Context context;
    private View view;
    private ChatItem item;

    public MessageViewHolder(View itemView) {
        super(itemView);

        textViewMessage = itemView.findViewById(R.id.text_view_message);
    }

    @Override
    public void init(ChatItem chatItem, Context context, View view) {
        this.context = context;
        this.view = view;
        this.item = chatItem;
        setMessage(((MessageItem) chatItem).getMessage());
    }

    public void setMessage(String message) {
        LinearLayout linearLayout;
        switch (getItemViewType()) {
            case MessageItem.TYPE_INCOMING_MESSAGE:
                linearLayout = view.findViewById(R.id.bubble_layout);
                linearLayout.setGravity(Gravity.RIGHT);
                textViewMessage.setBackground(ContextCompat.getDrawable(context, R.drawable.incoming_chat_bubble));
//                if (lastPosition != -1 && messageItems.get(lastPosition).getType() == MessageItem.TYPE_INCOMING_MESSAGE) {
//                    textViewMessage.setBackground(ContextCompat.getDrawable(context, R.drawable.incoming_continous_chat_bubble));
//                } else {
//                    textViewMessage.setBackground(ContextCompat.getDrawable(context, R.drawable.incoming_chat_bubble));
//                }
                break;
            case MessageItem.TYPE_OUTGOING_MESSAGE:
                linearLayout = view.findViewById(R.id.bubble_layout);
                linearLayout.setGravity(Gravity.LEFT);
                textViewMessage.setBackground(ContextCompat.getDrawable(context, R.drawable.outgoing_chat_bubble));
//                if (lastPosition != -1 && messageItems.get(lastPosition).getType() == MessageItem.TYPE_OUTGOING_MESSAGE) {
//                    textViewMessage.setBackground(ContextCompat.getDrawable(context, R.drawable.outgoing_continous_chat_bubble));
//                } else {
//                    textViewMessage.setBackground(ContextCompat.getDrawable(context, R.drawable.outgoing_chat_bubble));
//                }
                break;
            default:
                break;
        }
        if (null == textViewMessage)
            return;
        textViewMessage.setText(message);

//        setAnimation();
    }

    private void setAnimation(int position, int type) {
        if (position > lastPosition) {
            Animation animation;
            if (type == MessageItem.TYPE_OUTGOING_MESSAGE) {
                animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_left);
            } else {
                animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_right);
            }
            view.startAnimation(animation);
            lastPosition = position;
        }
    }

}