package com.chatapp.sp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        View v;

        layout = R.layout.item_message;
        v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);

        return new ViewHolder(v);
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
