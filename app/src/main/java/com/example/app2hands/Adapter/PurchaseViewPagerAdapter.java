package com.example.app2hands.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.app2hands.Fragment.PurchaseHistory.PurchaseOrderFragment;
import com.example.app2hands.Fragment.PurchaseHistory.PurchasedFragment;

public class PurchaseViewPagerAdapter extends FragmentStatePagerAdapter {
    public PurchaseViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new PurchaseOrderFragment();
            case 1:
                return new PurchasedFragment();
            default:
                return new PurchaseOrderFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return  "Đơn đang mua";
            case 1:
                return "Đơn đã mua";
            default:
                return "Đơn đang mua";
        }
    }
}
