package com.epicodus.slofamfun;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class YelpActivity extends AppCompatActivity {
    @Bind(R.id.yelpList) ListView mYelpList;
    private String[] kidActivity = new String[] {"SLO Childrens Museum", "Mitchell Park", "Pismo Beach", "Avila Beach", "Bishop Peak"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yelp);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, kidActivity);
        mYelpList.setAdapter(adapter);
    }

}
