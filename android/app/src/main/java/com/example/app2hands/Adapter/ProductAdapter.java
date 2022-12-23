package com.example.app2hands.Adapter;

import static com.example.app2hands.Api.ApiService.DOMAIN;
import static com.example.app2hands.MainActivity.CURR_NAV;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app2hands.Model.Product;
import com.example.app2hands.ProductDetail;
import com.example.app2hands.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private ArrayList<Product> listProduct;
    private Context context;

    public ProductAdapter(ArrayList<Product> listProduct, Context context) {
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
        Glide.with(context).load(DOMAIN + product.getImage()).into(holder.ivImg);
        holder.tvProductName.setText(product.getName());
        holder.tvProductPrice.setText(product.getPrice());
        holder.tvProductStatus.setText(product.getStatus());

        String sellStatus;
        switch (product.getSellStatus()) {
            case "pending":
                sellStatus = "Chờ duyệt";
                break;
            case "shipping":
                sellStatus = "Đang vận chuyển";
                break;
            case "sold":
                sellStatus = "Đã bán";
                break;
            default:
                sellStatus = "";
                break;
        }
        holder.tvSellStatus.setText(sellStatus);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetail.class);
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
        TextView tvProductName, tvProductPrice, tvProductStatus, tvSellStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImg = itemView.findViewById(R.id.productImage);
            tvProductName = itemView.findViewById(R.id.productName);
            tvProductPrice = itemView.findViewById(R.id.productPrice);
            tvProductStatus = itemView.findViewById(R.id.productStatus);
            tvSellStatus = itemView.findViewById(R.id.tvStatus);

        }
    }
}
