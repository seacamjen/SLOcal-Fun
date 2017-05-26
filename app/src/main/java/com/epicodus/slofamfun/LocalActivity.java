package com.epicodus.slofamfun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LocalActivity extends AppCompatActivity {
    @Bind(R.id.localList) ListView mLocalList;
    @Bind(R.id.searchedTextView) TextView mSearchedTextView;
    private String[] activities = new String[] {"Bishops Peak", "Montana De Oro", "SLO Childrens Museum", "Avila Valley Barn", "Atascadero Zoo"};
    private String[] cities = new String[] {"San Luis Obispo", "Los Osos", "San Luis Obispo", "Avila Beach", "Atascadero"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String search = intent.getStringExtra("search");
        mSearchedTextView.setText("Here are the results filtered by: " + search);

        LocalActivitiesArrayAdapter adapter = new LocalActivitiesArrayAdapter(this, android.R.layout.simple_list_item_1, activities, cities);
        mLocalList.setAdapter(adapter);
    }

}
