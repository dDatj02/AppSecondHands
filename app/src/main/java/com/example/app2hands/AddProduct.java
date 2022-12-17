package com.example.app2hands;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class AddProduct extends AppCompatActivity {
    Button btnSelectImage;
    private static final int Read_Permission = 101;
    ArrayList<Uri> uri = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        btnSelectImage = findViewById(R.id.btnSelectImage);

        if(ContextCompat.checkSelfPermission(AddProduct.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(AddProduct.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Read_Permission);
        }


        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                }
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            if(data.getClipData() != null){
                int x = data.getClipData().getItemCount();

                for(int i = 0; i<x; i++){
                    uri.add(data.getClipData().getItemAt(i).getUri());
                }
            }
        }
        else if (data.getData() != null){
            String imageURL = data.getData().getPath();
            uri.add(Uri.parse(imageURL));
        }
    }
}