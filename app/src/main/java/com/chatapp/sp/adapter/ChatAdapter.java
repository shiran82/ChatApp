package com.chatapp.sp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.chatapp.sp.R;
import com.chatapp.sp.adapter.viewholder.ChatLineViewHolder;
import com.chatapp.sp.adapter.viewholder.MessageViewHolder;
import com.chatapp.sp.adapter.viewholder.TimestampViewHolder;
import com.chatapp.sp.module.ChatItem;
import com.chatapp.sp.module.MessageItem;
import com.chatapp.sp.module.TimestampItem;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatLineViewHolder> {
    private List<ChatItem> chatItems;
    private Context context;
    private int lastPosition = -1;

    public ChatAdapter(Context context, List<ChatItem> chatItems) {
        this.chatItems = chatItems;
        this.context = context;
    }

    @Override
    public ChatLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        ChatLineViewHolder viewHolder = null;

            switch (viewType) {
                case MessageItem.TYPE_INCOMING_MESSAGE:
                case MessageItem.TYPE_OUTGOING_MESSAGE:
                    viewHolder = new MessageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R
                        .layout.item_message, parent, false));
                    break;
                case TimestampItem.TYPE_TIMESTAMP:
                    viewHolder = new TimestampViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                        .item_timestamp, parent, false));
                    break;
                default:
                    break;
            }

//            textView.setPadding(10, 10, 10, 10);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ChatLineViewHolder chatLineViewHolder, int position) {
//        MessageItem messageItem = messageItems.get(position);
//        messageViewHolder.setMessage(messageItem.getMessage());

        chatLineViewHolder.init(chatItems.get(position), context, chatLineViewHolder.itemView);

//        setAnimation(chatLineViewHolder.itemView, position, messageItem.getType());

    }

//    private void setAnimation(View viewToAnimate, int position, int type) {
//        if (position > lastPosition) {
//            Animation animation;
//            if (type == MessageItem.TYPE_OUTGOING_MESSAGE) {
//                animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_left);
//            } else {
//                animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_right);
//            }
//            viewToAnimate.startAnimation(animation);
//            lastPosition = position;
//        }
//    }

    @Override
    public int getItemViewType(int position) {
        return chatItems.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return chatItems.size();
    }

    public void add(ChatItem messageItem) {
        chatItems.add(messageItem);
    }
}
