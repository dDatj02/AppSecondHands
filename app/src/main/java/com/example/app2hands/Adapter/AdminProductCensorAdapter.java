package com.example.app2hands.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2hands.AdminProductCensorDetail;
import com.example.app2hands.AdminProductDetail;
import com.example.app2hands.Model.Product;
import com.example.app2hands.ProductDetail;
import com.example.app2hands.R;

import java.util.ArrayList;

public class AdminProductCensorAdapter extends RecyclerView.Adapter<AdminProductCensorAdapter.ViewHolder> {
    private ArrayList<Product> listProduct;
    private Context context;

    public AdminProductCensorAdapter(ArrayList<Product> listProduct, Context context) {
        this.listProduct = listProduct;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View productView = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(productView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = listProduct.get(position);

        holder.ivImg.setImageResource(product.getImg());
        holder.tvProductName.setText(product.getProductName());
        holder.tvProductPrice.setText(product.getProductPrice());
        holder.tvProductStatus.setText(product.getProductStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AdminProductCensorDetail.class);
                intent.putExtra(ProductDetail.EXTRA_PRODUCT, product);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImg;
        TextView tvProductName, tvProductPrice, tvProductStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImg = itemView.findViewById(R.id.productImage);
            tvProductName = itemView.findViewById(R.id.productName);
            tvProductPrice = itemView.findViewById(R.id.productPrice);
            tvProductStatus = itemView.findViewById(R.id.productStatus);
        }
    }
}
