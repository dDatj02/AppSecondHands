package com.example.app2hands;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.app2hands.Model.Notification;

public class AddNoti extends AppCompatActivity {
    EditText edtDescription, edtTitle;
    Button btnAddNoti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_noti);

        edtDescription = findViewById(R.id.edtDescription);
        edtTitle = findViewById(R.id.edtTitle);
        btnAddNoti = findViewById(R.id.btnAdd);

        btnAddNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edtTitle.getText().toString();
                String description = edtDescription.getText().toString();

                Notification notification = new Notification(title, description);
            }
        });
    }
}