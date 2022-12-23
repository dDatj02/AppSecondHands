package com.example.app2hands;

import static com.example.app2hands.Api.ApiService.DOMAIN;
import static com.example.app2hands.MainActivity.CURR_NAV;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.app2hands.Model.Product;

public class ProductDetail extends AppCompatActivity {
    public static final String EXTRA_PRODUCT = "extra_product";
    ImageView arrowBack, ivAvt, ivImage;
    TextView productPrice, productName, productStatus, productDesc, productType, tvSeller;
    Button buyBtn, delBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ///Back Arrow
        arrowBack = findViewById(R.id.arrowBack);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ///Detail Product Infomation
        Product product = getIntent().getParcelableExtra(EXTRA_PRODUCT);
        productPrice = findViewById(R.id.productPriceDetail);
        productName = findViewById(R.id.productNameDetail);
        productDesc = findViewById(R.id.tvDescribe);
        productType = findViewById(R.id.tvType);
        productStatus = findViewById(R.id.tvStatus);
        tvSeller = findViewById(R.id.tvSeller);
        ivAvt = findViewById(R.id.ivAvatar);
        ivImage = findViewById(R.id.ivImage);
        buyBtn = findViewById(R.id.buyBtn);
        delBtn = findViewById(R.id.delBtn);

        productPrice.setText(product.getPrice());
        productName.setText(product.getName());
        productStatus.setText(product.getStatus());
        productDesc.setText(product.getDescription());
        productType.setText(product.getType());
        tvSeller.setText(product.getSellerName());
        Glide.with(ProductDetail.this).load(DOMAIN + product.getSellerAvt()).into(ivAvt);
        Glide.with(ProductDetail.this).load(DOMAIN + product.getImage()).into(ivImage);

        if (CURR_NAV.equals("home")) buyBtn.setVisibility(View.VISIBLE);
        if (CURR_NAV.equals("store")) delBtn.setVisibility(View.VISIBLE);

        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
