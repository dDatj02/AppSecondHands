package com.example.app2hands;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.app2hands.Model.Notification;
import com.example.app2hands.Model.Product;

public class NotificationDetail extends AppCompatActivity {
    public static final String EXTRA_NOTIFICATION = "extra_notification";
    TextView title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);

        Notification notification = getIntent().getParcelableExtra(EXTRA_NOTIFICATION);
        title = findViewById(R.id.tvTitle);
        description = findViewById(R.id.tvDescription);

        title.setText(notification.getTitle());
        description.setText(notification.getDescription());
    }
}