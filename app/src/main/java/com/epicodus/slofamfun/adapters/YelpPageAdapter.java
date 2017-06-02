package com.epicodus.slofamfun.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.slofamfun.models.Activity;
import com.epicodus.slofamfun.ui.YelpDetailFragment;

import java.util.ArrayList;

public class YelpPageAdapter extends FragmentPagerAdapter {
    private ArrayList<Activity> mActivities;

    public YelpPageAdapter(FragmentManager fm, ArrayList<Activity> activities) {
        super(fm);
        mActivities = activities;
    }

    @Override
    public Fragment getItem(int position) {
        return YelpDetailFragment.newInstance(mActivities.get(position));
    }

    @Override
    public int getCount() {
        return mActivities.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mActivities.get(position).getName();
    }
}
