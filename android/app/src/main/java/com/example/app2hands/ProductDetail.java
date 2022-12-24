package com.example.app2hands;

import static com.example.app2hands.Api.ApiService.DOMAIN;
import static com.example.app2hands.Login.USER;
import static com.example.app2hands.MainActivity.CURR_NAV;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.app2hands.Api.ApiService;
import com.example.app2hands.Model.Product;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetail extends AppCompatActivity {
    public static final String EXTRA_PRODUCT = "extra_product";
    ImageView arrowBack, ivAvt, ivImage;
    TextView productPrice, productName, productStatus, productDesc, productType, tvSeller;
    Button buyBtn, delBtn, doneBtn, censorBtn;
    ProgressDialog dialog;
    LinearLayout storeManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ///Detail Product Infomation
        Product product = getIntent().getParcelableExtra(EXTRA_PRODUCT);
        initUi();

        ///Back Arrow
        arrowBack = findViewById(R.id.arrowBack);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        productPrice.setText(product.getPrice());
        productName.setText(product.getName());
        productStatus.setText(product.getStatus());
        productDesc.setText(product.getDescription());
        productType.setText(product.getType());
        tvSeller.setText(product.getSellerName());
        Glide.with(ProductDetail.this).load(DOMAIN + product.getSellerAvt()).into(ivAvt);
        Glide.with(ProductDetail.this).load(DOMAIN + product.getImage()).into(ivImage);

        if (CURR_NAV.equals("home") && !product.getSellerName().equals(USER.getName())) buyBtn.setVisibility(View.VISIBLE);
        if (CURR_NAV.equals("store")) storeManager.setVisibility(View.VISIBLE);
        if (CURR_NAV.equals("censor")) censorBtn.setVisibility(View.VISIBLE);

        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                HashMap<String, String> map = new HashMap<>();
                map.put("productId", product.getId());
                ApiService.api.executeBuyProduct(USER.getId(), map).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            dialog.dismiss();
                            Intent intent = new Intent(ProductDetail.this,
                                    MainActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(ProductDetail.this, "Mua sản phẩm thất bại",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                HashMap<String, String> map = new HashMap<>();
                map.put("productId", product.getId());
                ApiService.api.executeDeleteProduct(USER.getId(), map).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            dialog.dismiss();
                            Intent intent = new Intent(ProductDetail.this,
                                    MainActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(ProductDetail.this, "Xoá sản phẩm thất bại",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                HashMap<String, String> map = new HashMap<>();
                map.put("productId", product.getId());
                ApiService.api.executeDoneOrder(USER.getId(), map).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            dialog.dismiss();
                            Intent intent = new Intent(ProductDetail.this,
                                    MainActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(ProductDetail.this, "Xoá sản phẩm thất bại",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        censorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                HashMap<String, String> map = new HashMap<>();
                map.put("productId", product.getId());
                ApiService.api.executeCensorProduct(map).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            dialog.dismiss();
                            Intent intent = new Intent(ProductDetail.this,
                                    AdminActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(ProductDetail.this, "Duyệt sản phẩm thất bại",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void initUi() {
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
        doneBtn = findViewById(R.id.doneBtn);
        censorBtn = findViewById(R.id.censorBtn);
        storeManager = findViewById(R.id.storeManager);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");
    }
}
