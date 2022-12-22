package com.example.app2hands.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2hands.Model.Product;
import com.example.app2hands.R;

import java.util.ArrayList;

public class ImageProductAdapter extends RecyclerView.Adapter<ImageProductAdapter.ViewHolder>{
    private ArrayList<Uri> listUri;
    private Context context;

    public ImageProductAdapter(ArrayList<Uri> listUri, Context context) {
        this.listUri = listUri;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.image_product_detail, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ivImage.setImageURI(listUri.get(position));
    }

    @Override
    public int getItemCount() {
        return listUri.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.ivImage);
        }
    }
}
