package com.example.app2hands;

import static com.example.app2hands.Login.USER;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.app2hands.Api.ApiService;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity {
    ImageView arrowBack;
    EditText oldPass, newPass, re_newPass;
    AppCompatButton submitBtn;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        arrowBack = findViewById(R.id.arrowBack);
        oldPass = findViewById(R.id.oldPassword);
        newPass = findViewById(R.id.newPassword);
        re_newPass = findViewById(R.id.re_newPassword);
        submitBtn = findViewById(R.id.changePassButton);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");

        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPw = oldPass.getText().toString();
                String newPw = newPass.getText().toString();
                String re_newPw = re_newPass.getText().toString();

                if (oldPw.isEmpty() || newPw.isEmpty() || re_newPw.isEmpty()) {
                    Toast.makeText(ChangePassword.this, "Vui lòng nhập mật khẩu",
                            Toast.LENGTH_SHORT).show();
                } else if (!newPw.equals(re_newPw)) {
                    Toast.makeText(ChangePassword.this, "Mật khẩu không khớp",
                            Toast.LENGTH_SHORT).show();
                } else {
                    dialog.show();
                    HashMap<String, String> map = new HashMap<>();
                    map.put("_id", USER.getId());
                    map.put("oldPassword", oldPw);
                    map.put("newPassword", newPw);

                    ApiService.api.executeChangePassword(map).enqueue(new Callback<Void>() {
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
                            Toast.makeText(ChangePassword.this, "Đổi mật khẩu thất bại",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}