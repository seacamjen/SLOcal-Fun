package com.epicodus.slofamfun;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class YelpActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = YelpActivity.class.getSimpleName();
    @Bind(R.id.localActivites) Button mLocalActivites;
    @Bind(R.id.yelpList) ListView mYelpList;
    private String[] kidActivity = new String[] {"SLO Childrens Museum", "Mitchell Park", "Pismo Beach", "Avila Beach", "Bishop Peak"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yelp);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, kidActivity);
        mYelpList.setAdapter(adapter);

        mLocalActivites.setOnClickListener(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

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
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
