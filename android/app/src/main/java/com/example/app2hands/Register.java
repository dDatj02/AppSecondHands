package com.example.app2hands;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app2hands.Api.ApiService;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    TextView tvSignIn;
    EditText edtName, edtEmail, edtPassword, edtRePassword;
    AppCompatButton registerBtn;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tvSignIn = findViewById(R.id.tvSignIn);
        edtName = findViewById(R.id.editTextName);
        edtEmail = findViewById(R.id.editTextEmail);
        edtPassword = findViewById(R.id.editTextPassword);
        edtRePassword = findViewById(R.id.editRePassword);
        registerBtn = findViewById(R.id.registerBtn);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Register.this, "Incorrect input",
                            Toast.LENGTH_SHORT).show();
                } else if (!password.equals(edtRePassword.getText().toString())) {
                    Toast.makeText(Register.this, "Re-Password incorrect",
                            Toast.LENGTH_SHORT).show();
                } else {
                    dialog.show();
                    HashMap<String, String> map = new HashMap<>();
                    map.put("name", name);
                    map.put("email", email);
                    map.put("password", password);

                    ApiService.api.executeRegister(map).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            dialog.dismiss();
                            if (response.isSuccessful()) {
                                Intent intent = new Intent(Register.this, Login.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            dialog.dismiss();
                            Toast.makeText(Register.this, "Register fail",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}