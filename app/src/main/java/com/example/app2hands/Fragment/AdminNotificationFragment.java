package com.example.app2hands.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.app2hands.Adapter.NotificationAdapter;
import com.example.app2hands.AddNoti;
import com.example.app2hands.Model.Notification;
import com.example.app2hands.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminNotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminNotificationFragment extends Fragment {
    RecyclerView rvManageNoti;
    Button addNoti;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdminNotificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminNotification.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminNotificationFragment newInstance(String param1, String param2) {
        AdminNotificationFragment fragment = new AdminNotificationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_notification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvManageNoti = view.findViewById(R.id.rvManageNoti);
        ArrayList<Notification> notifications = (ArrayList<Notification>) initData();

        NotificationAdapter adapter = new NotificationAdapter(notifications, getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        rvManageNoti.setAdapter(adapter);
        rvManageNoti.setLayoutManager(linearLayoutManager);

        addNoti = view.findViewById(R.id.btnAdd);
        addNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddNoti.class);
                startActivity(intent);
            }
        });
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