package com.example.app2hands;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.app2hands.Adapter.NotificationAdapter;
import com.example.app2hands.Model.Notification;

import java.util.ArrayList;
import java.util.List;

public class UserNotification extends AppCompatActivity {
    RecyclerView rvUserNoti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_notification);

        rvUserNoti = findViewById(R.id.rvUserNoti);
        ArrayList<Notification> notifications = (ArrayList<Notification>) initData();

        NotificationAdapter adapter = new NotificationAdapter(notifications, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        rvUserNoti.setAdapter(adapter);
        rvUserNoti.setLayoutManager(linearLayoutManager);
    }

    public List<Notification> initData(){
        String[] title = {"Iphone 14", "Iphone 14 Pro Max"};
        String[] description = {"Việc hiển thị text trên TextView khá là phức tạp, bao gồm các tính năng như multiple font, khoảng cách giữa các dòng, các chữ với nhau, hướng text hiển thị, line breaking, các gách nối v.vv.. TextView phải làm quá nhiều việc để tính toán và bố trí các Text được nhận như: đọc file font, tìm các ký tự hình tượng, chia các shape cho text, tính toán phần bounding và caching các text vào bộ đệm. Hơn nữa, tất cả các công việc trên đều hoạt động trên UI Thread, và nó có khả năng làm giảm hiệu năng của ứng dụng.", "Deep Việc hiển thị text trên TextView khá là phức tạp, bao gồm các tính năng như multiple font, khoảng cách giữa các dòng, các chữ với nhau, hướng text hiển thị, line breaking, các gách nối v.vv.. TextView phải làm quá nhiều việc để tính toán và bố trí các Text được nhận như: đọc file font, tìm các ký tự hình tượng, chia các shape cho text, tính toán phần bounding và caching các text vào bộ đệm. Hơn nữa, tất cả các công việc trên đều hoạt động trên UI Thread, và nó có khả năng làm giảm hiệu năng của ứng dụng."};
        List<Notification> notificationList = new ArrayList<>();
        for (int i=0; i<title.length;i++){
            Notification p = new Notification(title[i], description[i]);
            notificationList.add(p);
        }
        return notificationList;
    }
}