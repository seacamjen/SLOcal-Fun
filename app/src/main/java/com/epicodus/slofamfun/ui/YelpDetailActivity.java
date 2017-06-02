package com.epicodus.slofamfun.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.slofamfun.R;
import com.epicodus.slofamfun.adapters.YelpPageAdapter;
import com.epicodus.slofamfun.models.Activity;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class YelpDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private YelpPageAdapter adapterViewPager;
    ArrayList<Activity> mActivities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yelp_detail);
        ButterKnife.bind(this);

        mActivities = Parcels.unwrap(getIntent().getParcelableExtra("activities"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new YelpPageAdapter(getSupportFragmentManager(), mActivities);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
