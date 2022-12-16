package com.example.app2hands;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NotificationDetail extends AppCompatActivity {
    public static final String EXTRA_NOTIFICATION = "extra_notification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);
    }
}