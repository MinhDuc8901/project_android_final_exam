package com.example.project.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.project.fragment.ClassroomFragment;
import com.example.project.fragment.HomeFragment;
import com.example.project.fragment.StudentFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new HomeFragment();
            case 1: return new StudentFragment();
            case 2: return new ClassroomFragment();
            default: return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
