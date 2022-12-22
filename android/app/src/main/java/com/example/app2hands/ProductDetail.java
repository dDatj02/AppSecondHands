package com.example.app2hands;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.app2hands.Adapter.ImageProductAdapter;
import com.example.app2hands.Model.Product;

import java.util.ArrayList;

public class ProductDetail extends AppCompatActivity {
    public static final String EXTRA_PRODUCT = "extra_product";
    ImageView arrowBack;
    TextView productPrice, productName, productStatus, productDesc, productType;
    RecyclerView rvImageDetailProduct;
    ArrayList<Uri> listUri = new ArrayList<>();

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

        productPrice.setText(product.getPrice());
        productName.setText(product.getName());
        productStatus.setText(product.getStatus());
        productDesc.setText(product.getDescription());
        productType.setText(product.getType());


        //Slider
        rvImageDetailProduct = findViewById(R.id.rvImageDetailProduct);
        ImageProductAdapter adapter = new ImageProductAdapter(listUri, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        rvImageDetailProduct.setAdapter(adapter);
        rvImageDetailProduct.setLayoutManager(linearLayoutManager);

    }
}
