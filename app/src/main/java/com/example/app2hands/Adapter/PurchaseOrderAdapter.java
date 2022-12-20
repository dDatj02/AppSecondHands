package com.example.app2hands.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2hands.Model.Product;
import com.example.app2hands.R;

import java.util.ArrayList;

public class PurchaseOrderAdapter extends RecyclerView.Adapter<PurchaseOrderAdapter.ViewHolder> {
    private ArrayList<Product> listProduct;
    private Context context;

    public PurchaseOrderAdapter(ArrayList<Product> listProduct, Context context) {
        this.listProduct = listProduct;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View purchaseView = LayoutInflater.from(context).inflate(R.layout.purchase_order_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(purchaseView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = listProduct.get(position);

        holder.ivImg.setImageURI(product.getImg().get(0));
        holder.tvName.setText(product.getProductName());
        holder.tvPrice.setText(product.getProductPrice());
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivImg;
        TextView tvName, tvPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImg = itemView.findViewById(R.id.ivImg);
            tvName = itemView.findViewById(R.id.tvProductName);
            tvPrice = itemView.findViewById(R.id.tvProductPrice);
        }
    }
}
