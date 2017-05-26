package com.epicodus.slofamfun;


import android.content.Context;
import android.widget.ArrayAdapter;

public class LocalActivitiesArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mActivities;
    private String[] mCities;

    public LocalActivitiesArrayAdapter(Context mContext, int resource, String[] mActivities, String[] mCities) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mActivities = mActivities;
        this.mCities = mCities;
    }

    @Override
    public Object getItem(int position) {
        String activity = mActivities[position];
        String city = mCities[position];
        return String.format("%s \nCity:  %s", activity, city);
    }

    @Override
    public int getCount() {
        return mActivities.length;
    }
}
