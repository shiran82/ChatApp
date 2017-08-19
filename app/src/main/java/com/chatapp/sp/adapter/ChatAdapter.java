package com.chatapp.sp.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chatapp.sp.R;
import com.chatapp.sp.module.Message;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private List<Message> messages;
    private Context context;

    public ChatAdapter(Context context, List<Message> messages) {
        this.messages = messages;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = -1;
        View view;

        layout = R.layout.item_message;
        view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);

        TextView textView = view.findViewById(R.id.text_view_message);
        ;
        switch (viewType) {
            case Message.TYPE_INCOMING_MESSAGE:
                LinearLayout linearLayout = view.findViewById(R.id.bubble_layout);
                linearLayout.setGravity(Gravity.RIGHT);
                textView.setBackground(ContextCompat.getDrawable(context, R.drawable.incoming_chat_bubble));
                break;
            case Message.TYPE_OUTGOING_MESSAGE:
                textView.setBackground(ContextCompat.getDrawable(context, R.drawable.outgoing_chat_bubble));
                break;
            default:
                break;
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Message message = messages.get(position);
        viewHolder.setMessage(message.getMessage());

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return messages.get(position).getType();
    }

    public void add(Message message) {
        messages.add(message);
    }

    public Message getItem(int i) {
        return messages.get(i);
    }

    public void remove(int i) {
        messages.remove(i);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewMessage;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewMessage = itemView.findViewById(R.id.text_view_message);
        }

        public void setMessage(String message) {
            if (null == textViewMessage)
                return;
            textViewMessage.setText(message);
        }
    }
}
