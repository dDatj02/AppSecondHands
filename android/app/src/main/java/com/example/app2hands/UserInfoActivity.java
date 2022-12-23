package com.example.app2hands;

import static com.example.app2hands.Api.ApiService.DOMAIN;
import static com.example.app2hands.Login.USER;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.app2hands.Api.ApiService;
import com.example.app2hands.Api.RealPathUtil;
import com.example.app2hands.Model.User;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInfoActivity extends AppCompatActivity {
    EditText edtName, edtPhone, edtAddress;
    ImageView ivAvatar;
    Button btnSave;
    ProgressDialog dialog;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        btnSave = findViewById(R.id.btnSave);
        edtName = findViewById(R.id.edtUserName);
        edtPhone = findViewById(R.id.edtPhone);
        edtAddress = findViewById(R.id.edtAddress);
        ivAvatar = findViewById(R.id.ivAvatar);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");

        Glide.with(UserInfoActivity.this).load(DOMAIN + USER.getAvatar()).into(ivAvatar);
        edtName.setText(USER.getName());
        edtPhone.setText(USER.getPhone());
        edtAddress.setText(USER.getAddress());

        ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRequestPermission();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });
    }

    private void updateProfile() {
        String name = edtName.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();

        if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            Toast.makeText(UserInfoActivity.this, "Vui lòng nhập đầy đủ thông tin",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        dialog.show();

        RequestBody rbName = RequestBody.create(MediaType.parse("multipart/form-data"), name);
        RequestBody rbPhone = RequestBody.create(MediaType.parse("multipart/form-data"), phone);
        RequestBody rbAddress = RequestBody.create(MediaType.parse("multipart/form-data"), address);

        RequestBody rbImage = RequestBody.create(MediaType.parse("multipart/form-data"), "");
        MultipartBody.Part multiPart = MultipartBody.Part
                .createFormData("user", "", rbImage);

        if (uri != null) {
            String path = RealPathUtil.getRealPath(this, uri);
            File file = new File(path);
            rbImage = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            multiPart = MultipartBody.Part
                    .createFormData("user", file.getName(), rbImage);
        }

        ApiService.api.executeUpdateProfile(USER.getId(), rbName, rbPhone, rbAddress, multiPart)
                .enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    USER = response.body();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(UserInfoActivity.this, "Chỉnh sửa profile thất bại",
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
                            ivAvatar.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );
}