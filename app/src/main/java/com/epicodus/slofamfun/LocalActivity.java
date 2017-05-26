package com.epicodus.slofamfun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LocalActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.localList) ListView mLocalList;
    private String[] activities = new String[] {"Bishops Peak", "Montana De Oro", "SLO Childrens Museum", "Avila Valley Barn", "Atascadero Zoo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, activities);
        mLocalList.setAdapter(adapter);
    }

}
