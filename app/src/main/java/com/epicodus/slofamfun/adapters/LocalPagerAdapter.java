package com.epicodus.slofamfun.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.epicodus.slofamfun.models.LocalActivity;
import com.epicodus.slofamfun.ui.LocalDetailFragment;

import java.util.ArrayList;

public class LocalPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<LocalActivity> mLocalActivities;

    public LocalPagerAdapter(FragmentManager fm, ArrayList<LocalActivity> localActivities) {
        super(fm);
        mLocalActivities = localActivities;
    }

    @Override
    public Fragment getItem(int position) {
        return LocalDetailFragment.newInstance(mLocalActivities.get(position));
    }

    @Override
    public int getCount() {
        return mLocalActivities.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mLocalActivities.get(position).getName();

    }
}
