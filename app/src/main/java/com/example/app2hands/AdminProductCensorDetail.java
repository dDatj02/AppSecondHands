package com.example.app2hands;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.app2hands.Model.Product;

import java.util.ArrayList;

public class AdminProductCensorDetail extends AppCompatActivity {
    public static final String EXTRA_PRODUCT = "extra_product";
    ImageSlider imageSlider;
    ImageView arrowBack;
    TextView productPrice, productName, productStatus, productType, productDescription;

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

        ///Slider
        imageSlider = findViewById(R.id.imageDetailProduct);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        ArrayList<Uri> imageTest = new ArrayList<>();


        slideModels.add(new SlideModel(R.drawable.anhtest, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.anhtest, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.anhtest, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.anhtest, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.anhtest, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);


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