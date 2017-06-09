package com.epicodus.slofamfun.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.slofamfun.R;
import com.epicodus.slofamfun.adapters.LocalPagerAdapter;
import com.epicodus.slofamfun.models.LocalActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LocalDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewLocalPager) ViewPager mViewLocalPager;
    private LocalPagerAdapter adapterLocalViewPager;
    ArrayList<LocalActivity> mLocalActivities = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_detail);
        ButterKnife.bind(this);

        mLocalActivities = Parcels.unwrap(getIntent().getParcelableExtra("localActivity"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterLocalViewPager = new LocalPagerAdapter(getSupportFragmentManager(), mLocalActivities);
        mViewLocalPager.setAdapter(adapterLocalViewPager);
        mViewLocalPager.setCurrentItem(startingPosition);
    }
}
