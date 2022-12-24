package com.example.app2hands;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import static com.example.app2hands.MainActivity.CURR_NAV;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.app2hands.Fragment.AdminCensorStoreFragment;
import com.example.app2hands.Fragment.PersonalFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AdminActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    PersonalFragment personalFragment = new PersonalFragment();
    AdminCensorStoreFragment adminCensorStoreFragment = new AdminCensorStoreFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        CURR_NAV = "censor";

        bottomNavigationView = findViewById(R.id.bottom_navigation_admin);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, adminCensorStoreFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.adminCensor:
                        CURR_NAV = "censor";
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, adminCensorStoreFragment).commit();
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