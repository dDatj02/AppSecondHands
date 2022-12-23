package com.example.app2hands;

import static com.example.app2hands.Login.USER;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app2hands.Api.ApiService;
import com.example.app2hands.Api.RealPathUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProduct extends AppCompatActivity {
    Button btnSelectImage, addBtn;
    EditText productName, productType, productStatus, productPrice, productDescription;
    ImageView imgGallery;
    ProgressDialog dialog;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        productName = findViewById(R.id.productName);
        productType = findViewById(R.id.productType);
        productStatus = findViewById(R.id.productStatus);
        productPrice = findViewById(R.id.productPrice);
        productDescription = findViewById(R.id.productDescription);
        btnSelectImage = findViewById(R.id.btnSelectImage);
        addBtn = findViewById(R.id.addBtn);
        imgGallery = findViewById(R.id.imgGallery);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");

        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRequestPermission();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProduct();
            }
        });
    }

    private void addProduct() {
        String name = productName.getText().toString().trim();
        String type = productType.getText().toString().trim();
        String status = productStatus.getText().toString().trim();
        String price = productPrice.getText().toString().trim();
        String description = productDescription.getText().toString().trim();

        if (name.isEmpty() || price.isEmpty()) {
            Toast.makeText(AddProduct.this, "Vui lòng nhập đầy đủ thông tin",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        dialog.show();

        RequestBody rbName = RequestBody.create(MediaType.parse("multipart/form-data"), name);
        RequestBody rbPrice = RequestBody.create(MediaType.parse("multipart/form-data"), price);
        RequestBody rbStatus = RequestBody.create(MediaType.parse("multipart/form-data"), status);
        RequestBody rbType = RequestBody.create(MediaType.parse("multipart/form-data"), type);
        RequestBody rbDescription = RequestBody.create(MediaType.parse("multipart/form-data"), description);

        String path = RealPathUtil.getRealPath(this, uri);
        File file = new File(path);
        RequestBody rbImage = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part multiPart = MultipartBody.Part
                .createFormData("product", file.getName(), rbImage);

        ApiService.api.executeSellProduct(USER.getId(), rbName, rbPrice, rbStatus, rbType,
                rbDescription, multiPart).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(AddProduct.this, "Thêm sản phẩm thất bại",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onClickRequestPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            openGallery();
            return;
        }

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else {
            String [] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            }
        }
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activityResultLauncher.launch(Intent.createChooser(intent, "Select image"));
    }

    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data == null) {
                            return;
                        }
                        uri = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            imgGallery.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );
}