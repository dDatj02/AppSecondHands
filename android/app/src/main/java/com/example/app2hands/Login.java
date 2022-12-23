package com.example.app2hands;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app2hands.Api.ApiService;
import com.example.app2hands.Model.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    TextView tvRegister;
    Button btnButton;
    EditText edtEmail, edtPassword;
    public static User USER;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvRegister = findViewById(R.id.tvRegister);
        btnButton = findViewById(R.id.cirLoginButton);
        edtEmail = findViewById(R.id.editTextEmail);
        edtPassword = findViewById(R.id.editTextPassword);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                finish();
            }
        });

        btnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Incorrect email or password",
                            Toast.LENGTH_SHORT).show();
                } else {
                    dialog.show();
                    HashMap<String, String> map = new HashMap<>();
                    map.put("email", email);
                    map.put("password", password);

                    ApiService.api.executeLogin(map)
                            .enqueue(new Callback<User>() {
                                @Override
                                public void onResponse(Call<User> call, Response<User> response) {
                                    dialog.dismiss();
                                    if (response.code() == 200) {
                                        USER = response.body();
                                        Intent intent;
                                        if (USER.getRole().equals("admin")) {
                                            intent = new Intent(Login.this,
                                                    AdminActivity.class);
                                        } else {
                                            intent = new Intent(Login.this,
                                                    MainActivity.class);
                                        }
                                        startActivity(intent);
                                        finish();
                                    }
                                }

                                @Override
                                public void onFailure(Call<User> call, Throwable t) {
                                    dialog.dismiss();
                                    Toast.makeText(Login.this, "Login fail",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }
}