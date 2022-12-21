package com.example.app2hands;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.app2hands.R;

public class UserInfoActivity extends AppCompatActivity {
    EditText edtUserName, edtPhone, edtBirth;
    ImageView ivAvatar;
    Button btnSave;
    int SELECT_IMAGE_CODE = 1;
    Uri avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        btnSave = findViewById(R.id.btnSave);
        edtUserName = findViewById(R.id.edtUserName);
        edtPhone = findViewById(R.id.edtPhone);
        edtBirth = findViewById(R.id.edtBirth);
        ivAvatar = findViewById(R.id.ivAvatar);


        ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Title"),SELECT_IMAGE_CODE);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtUserName.getText().toString();
                String Phone = edtPhone.getText().toString();
                String Birth = edtBirth.getText().toString();


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            avatar = data.getData();
            ivAvatar.setImageURI(avatar);
        }
    }
}