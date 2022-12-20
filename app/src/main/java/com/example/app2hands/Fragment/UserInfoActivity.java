package com.example.app2hands.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.app2hands.R;

public class UserInfoActivity extends AppCompatActivity {
    EditText edtUserName, edtPhone;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        btnSave = findViewById(R.id.btnSave);
        edtUserName = findViewById(R.id.edtUserName);
        edtPhone = findViewById(R.id.edtPhone);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtUserName.getText().toString();
                String Phone = edtPhone.getText().toString();
            }
        });

    }
}