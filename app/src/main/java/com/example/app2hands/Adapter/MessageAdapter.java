package com.example.app2hands.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2hands.Model.Message;
import com.example.app2hands.R;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private ArrayList<Message> listMessage;
    private Context context;

    public MessageAdapter(ArrayList<Message> listMessage, Context context) {
        this.listMessage = listMessage;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View messageView = LayoutInflater.from(context).inflate(R.layout.message_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(messageView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message message = listMessage.get(position);

        holder.ivImg.setImageResource(message.getImg());
        holder.tvName.setText(message.getName());
        holder.tvMsg.setText(message.getMsg());
    }

    @Override
    public int getItemCount() {
        return listMessage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivImg;
        TextView tvName, tvMsg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImg = itemView.findViewById(R.id.ivImg);
            tvName = itemView.findViewById(R.id.tvName);
            tvMsg = itemView.findViewById(R.id.tvMsg);
        }
    }
}
