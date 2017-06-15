package com.epicodus.slofamfun.ui;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.epicodus.slofamfun.Constants;
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

public class YelpFragment extends Fragment {
    private SharedPreferences mSharedPreferenes;
    private String mRecentAddress;

//    @Bind(R.id.localActivites) Button mLocalActivites;
    @Bind(R.id.yelpRecyclerView) RecyclerView mYelpRecyclerView;
    private YelpActivityListAdapter mYelpAdapter;

    public ArrayList<Activity> mActivities = new ArrayList<>();

    public YelpFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSharedPreferenes = PreferenceManager.getDefaultSharedPreferences(getActivity());
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

                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mYelpAdapter = new YelpActivityListAdapter(getActivity(), mActivities);
                        mYelpRecyclerView.setAdapter(mYelpAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        mYelpRecyclerView.setLayoutManager(layoutManager);
                        mYelpRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yelp, container, false);
        ButterKnife.bind(this, view);

        mRecentAddress = mSharedPreferenes.getString(Constants.PREFERENCES_LOCATION_KEY, null);
        if (mRecentAddress != null) {
            getActivities(mRecentAddress);
        }
        return view;
    }

}
