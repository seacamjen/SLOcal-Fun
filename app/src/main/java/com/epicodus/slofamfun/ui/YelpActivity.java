package com.epicodus.slofamfun.ui;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.epicodus.slofamfun.Constants;
import com.epicodus.slofamfun.LocalChoiceFragment;
import com.epicodus.slofamfun.R;
import com.epicodus.slofamfun.adapters.YelpActivityListAdapter;
import com.epicodus.slofamfun.models.Activity;
import com.epicodus.slofamfun.services.YelpService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class YelpActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = YelpActivity.class.getSimpleName();
    private SharedPreferences mSharedPreferenes;
    private String mRecentAddress;

    @Bind(R.id.localActivites) Button mLocalActivites;
    @Bind(R.id.yelpRecyclerView) RecyclerView mYelpRecyclerView;
    private YelpActivityListAdapter mYelpAdapter;

    public ArrayList<Activity> mActivities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yelp);
        ButterKnife.bind(this);

        mLocalActivites.setOnClickListener(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        mSharedPreferenes = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentAddress = mSharedPreferenes.getString(Constants.PREFERENCES_LOCATION_KEY, null);
        if (mRecentAddress != null) {
            getActivities(mRecentAddress);
        }

        getActivities(location);
    }

    private void getActivities(String location) {
        final YelpService yelpService = new YelpService();

        yelpService.findActivities(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mActivities = yelpService.processResults(response);

                YelpActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mYelpAdapter = new YelpActivityListAdapter(getApplicationContext(), mActivities);
                        mYelpRecyclerView.setAdapter(mYelpAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(YelpActivity.this);
                        mYelpRecyclerView.setLayoutManager(layoutManager);
                        mYelpRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == mLocalActivites) {
            FragmentManager fm = getFragmentManager();
            LocalChoiceFragment localChoiceFragment = new LocalChoiceFragment();
            localChoiceFragment.show(fm, "Sample Fragment");
        }
    }

}
