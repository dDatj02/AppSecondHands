package com.example.app2hands;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.app2hands.Fragment.HomeFragment;
import com.example.app2hands.Fragment.PurchaseFragment;
import com.example.app2hands.Fragment.PersonalFragment;
import com.example.app2hands.Fragment.StoreManageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    public static String CURR_NAV;

    HomeFragment homeFragment = new HomeFragment();
    PurchaseFragment messageFragment = new PurchaseFragment();
    StoreManageFragment storeManageFragment = new StoreManageFragment();
    PersonalFragment personalFragment = new PersonalFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CURR_NAV = "home";
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        CURR_NAV = "home";
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        return  true;
                    case R.id.message:
                        CURR_NAV = "order";
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, messageFragment).commit();
                        return  true;
                    case R.id.store:
                        CURR_NAV = "store";
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, storeManageFragment).commit();
                        return  true;
                    case R.id.personal:
                        CURR_NAV = "profile";
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, personalFragment).commit();
                        return  true;
                }

                return false;
            }
        });

    }
}