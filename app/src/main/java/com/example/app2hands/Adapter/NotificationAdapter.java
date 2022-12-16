package com.example.app2hands.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2hands.AdminProductDetail;
import com.example.app2hands.Model.Message;
import com.example.app2hands.Model.Notification;
import com.example.app2hands.NotificationDetail;
import com.example.app2hands.ProductDetail;
import com.example.app2hands.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private ArrayList<Notification> listNotification;
    private Context context;

    public NotificationAdapter(ArrayList<Notification> listNotification, Context context) {
        this.listNotification = listNotification;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View notificationView = LayoutInflater.from(context).inflate(R.layout.noti_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(notificationView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notification notification = listNotification.get(position);

        holder.tvTitle.setText(notification.getTitle());
        holder.tvDescription.setText(notification.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NotificationDetail.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNotification.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle, tvDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }
    }
}
