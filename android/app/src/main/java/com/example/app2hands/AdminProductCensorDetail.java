package com.example.app2hands;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app2hands.Adapter.ImageProductAdapter;
import com.example.app2hands.Model.Product;

import java.util.ArrayList;

public class AdminProductCensorDetail extends AppCompatActivity {
    public static final String EXTRA_PRODUCT = "extra_product";
    RecyclerView rvImageDetailProduct;
    ImageView arrowBack;
    TextView productPrice, productName, productStatus, productType, productDescription;
    ArrayList<Uri> listUri = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product_censor_detail);


        ///Detail Product Infomation
        Product product = getIntent().getParcelableExtra(EXTRA_PRODUCT);
        productPrice = findViewById(R.id.productPriceDetail);
        productName = findViewById(R.id.productNameDetail);
        productStatus = findViewById(R.id.tvStatus);
        productType = findViewById(R.id.tvType);
        productDescription = findViewById(R.id.tvDescribe);


        productPrice.setText(product.getProductPrice());
        productName.setText(product.getProductName());
        productStatus.setText(product.getProductStatus());
        productType.setText(product.getProductType());
        productDescription.setText(product.getProductDescription());
        listUri = product.getImg();

        ///Slider
        rvImageDetailProduct = findViewById(R.id.rvImageDetailProduct);
        ImageProductAdapter adapter = new ImageProductAdapter(listUri, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        rvImageDetailProduct.setAdapter(adapter);
        rvImageDetailProduct.setLayoutManager(linearLayoutManager);


        ///Back Arrow
        arrowBack = findViewById(R.id.arrowBack);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}