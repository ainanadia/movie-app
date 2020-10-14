package com.example.navigationdrawerfragments.adapter;

import com.example.navigationdrawerfragments.fragment.MessageFragment;
import com.example.navigationdrawerfragments.fragment.NotificationsFragment;
import com.example.navigationdrawerfragments.fragment.PromotionFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabAdapter extends FragmentStateAdapter {


    public TabAdapter(@NonNull NotificationsFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new MessageFragment();
            default:
                return new PromotionFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
