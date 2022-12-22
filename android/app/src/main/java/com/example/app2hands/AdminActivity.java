package com.example.app2hands;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.app2hands.Fragment.AdminCensorStoreFragment;
import com.example.app2hands.Fragment.AdminManageStoreFragment;
import com.example.app2hands.Fragment.AdminNotificationFragment;
import com.example.app2hands.Fragment.HomeFragment;
import com.example.app2hands.Fragment.PurchaseFragment;
import com.example.app2hands.Fragment.PersonalFragment;
import com.example.app2hands.Fragment.StoreManageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AdminActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    PurchaseFragment messageFragment = new PurchaseFragment();
    StoreManageFragment storeManageFragment = new StoreManageFragment();
    PersonalFragment personalFragment = new PersonalFragment();
    AdminManageStoreFragment adminManageStoreFragment = new AdminManageStoreFragment();
    AdminCensorStoreFragment adminCensorStoreFragment = new AdminCensorStoreFragment();
    AdminNotificationFragment adminNotificationFragment = new AdminNotificationFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        bottomNavigationView = findViewById(R.id.bottom_navigation_admin);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, adminManageStoreFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.adminManageStore:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, adminManageStoreFragment).commit();
                        return  true;
                    case R.id.adminCensor:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, adminCensorStoreFragment).commit();
                        return  true;
                    case R.id.adminNotification:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, adminNotificationFragment).commit();
                        return  true;
                }

                return false;
            }
        });

    }
}