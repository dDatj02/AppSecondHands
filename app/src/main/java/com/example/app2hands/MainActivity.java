package com.example.app2hands;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.app2hands.Fragment.HomeFragment;
import com.example.app2hands.Fragment.MessageFragment;
import com.example.app2hands.Fragment.PersonalFragment;
import com.example.app2hands.Fragment.StoreManageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    MessageFragment messageFragment = new MessageFragment();
    StoreManageFragment storeManageFragment = new StoreManageFragment();
    PersonalFragment personalFragment = new PersonalFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        return  true;
                    case R.id.message:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, messageFragment).commit();
                        return  true;
                    case R.id.store:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, storeManageFragment).commit();
                        return  true;
                    case R.id.personal:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, personalFragment).commit();
                        return  true;
                }

                return false;
            }
        });

    }
}