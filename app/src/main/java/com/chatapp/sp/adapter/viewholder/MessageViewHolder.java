package com.chatapp.sp.adapter.viewholder;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.chatapp.sp.Constant;
import com.chatapp.sp.R;
import com.chatapp.sp.databinding.ItemMessageBinding;
import com.chatapp.sp.module.ChatItem;
import com.chatapp.sp.module.MessageItem;

public class MessageViewHolder extends ChatLineViewHolder {
    private ItemMessageBinding binding;
    private int lastItemType;
    private Context context;
    private ChatItem item;
    private View view;

    public MessageViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void init(ChatItem chatItem, Context context, View view, int lastItemType) {
        this.context = context;
        this.view = view;
        this.lastItemType = lastItemType;
        this.item = chatItem;
        binding = DataBindingUtil.getBinding(itemView);
        setMessage(((MessageItem) chatItem).getMessage());
    }

    public void setMessage(String message) {
        LinearLayout linearLayout;
        switch (getItemViewType()) {
            case Constant.TYPE_INCOMING_MESSAGE:
                linearLayout = view.findViewById(R.id.bubble_layout);
                linearLayout.setGravity(Gravity.RIGHT);
                binding.textViewMessage.setBackground(ContextCompat.getDrawable(context, R.drawable.incoming_chat_bubble));
                if (lastItemType != 0 && lastItemType == Constant.TYPE_INCOMING_MESSAGE) {
                    binding.textViewMessage.setBackground(ContextCompat.getDrawable(context, R.drawable.incoming_continous_chat_bubble));
                } else {
                    binding.textViewMessage.setBackground(ContextCompat.getDrawable(context, R.drawable.incoming_chat_bubble));
                }
                break;
            case Constant.TYPE_OUTGOING_MESSAGE:
                linearLayout = view.findViewById(R.id.bubble_layout);
                linearLayout.setGravity(Gravity.LEFT);
                binding.textViewMessage.setBackground(ContextCompat.getDrawable(context, R.drawable.outgoing_chat_bubble));
                if (lastItemType != 0 && lastItemType == Constant.TYPE_OUTGOING_MESSAGE) {
                    binding.textViewMessage.setBackground(ContextCompat.getDrawable(context, R.drawable.outgoing_continous_chat_bubble));
                } else {
                    binding.textViewMessage.setBackground(ContextCompat.getDrawable(context, R.drawable.outgoing_chat_bubble));
                }
                break;
            default:
                break;
        }

        binding.textViewMessage.setPadding(16, 14, 16, 14);

        binding.textViewMessage.setText(message);

        setAnimation();
    }

    private void setAnimation() {
        if (item.isAnimate()) {
            Animation animation;
            if (getItemViewType() == Constant.TYPE_OUTGOING_MESSAGE) {
                animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_left);
            } else {
                animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_right);
            }
            view.startAnimation(animation);

            item.setAnimate(false);
        }
    }
}