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
    private int lastItemType;
    private Context context;
    private ChatItem item;
    private View view;

    public MessageViewHolder(View itemView) {
        super(itemView);

        textViewMessage = itemView.findViewById(R.id.text_view_message);
    }

    @Override
    public void init(ChatItem chatItem, Context context, View view, int lastItemType) {
        this.context = context;
        this.view = view;
        this.lastItemType = lastItemType;
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
                if (lastItemType != 0 && lastItemType == MessageItem.TYPE_INCOMING_MESSAGE) {
                    textViewMessage.setBackground(ContextCompat.getDrawable(context, R.drawable.incoming_continous_chat_bubble));
                } else {
                    textViewMessage.setBackground(ContextCompat.getDrawable(context, R.drawable.incoming_chat_bubble));
                }
                break;
            case MessageItem.TYPE_OUTGOING_MESSAGE:
                linearLayout = view.findViewById(R.id.bubble_layout);
                linearLayout.setGravity(Gravity.LEFT);
                textViewMessage.setBackground(ContextCompat.getDrawable(context, R.drawable.outgoing_chat_bubble));
                if (lastItemType != 0 && lastItemType == MessageItem.TYPE_OUTGOING_MESSAGE) {
                    textViewMessage.setBackground(ContextCompat.getDrawable(context, R.drawable.outgoing_continous_chat_bubble));
                } else {
                    textViewMessage.setBackground(ContextCompat.getDrawable(context, R.drawable.outgoing_chat_bubble));
                }
                break;
            default:
                break;
        }

        textViewMessage.setPadding(16, 14, 16, 14);

        textViewMessage.setText(message);

        setAnimation();
    }

    private void setAnimation() {
        if (item.isAnimate()) {
            Animation animation;
            if (getItemViewType() == MessageItem.TYPE_OUTGOING_MESSAGE) {
                animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_left);
            } else {
                animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_right);
            }
            view.startAnimation(animation);

            item.setAnimate(false);
        }
    }

}