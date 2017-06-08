package com.epicodus.slofamfun.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.slofamfun.R;
import com.epicodus.slofamfun.adapters.LocalActivitiesArrayAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LocalActivity extends AppCompatActivity {
//    @Bind(R.id.localList) ListView mLocalList;
//    @Bind(R.id.searchedTextView) TextView mSearchedTextView;
    @Bind(R.id.localRecyclerView) RecyclerView mLocalRecyclerView;
    private String[] activities = new String[] {"Bishops Peak", "Montana De Oro", "SLO Childrens Museum", "Avila Valley Barn", "Atascadero Zoo"};
    private String[] cities = new String[] {"San Luis Obispo", "Los Osos", "San Luis Obispo", "Avila Beach", "Atascadero"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        ButterKnife.bind(this);

//        Intent intent = getIntent();
//        String search = intent.getStringExtra("search");
//        mSearchedTextView.setText("Here are the results filtered by: " + search);

//        LocalActivitiesArrayAdapter adapter = new LocalActivitiesArrayAdapter(this, android.R.layout.simple_list_item_1, activities, cities);
//        mLocalList.setAdapter(adapter);
    }

    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            Intent intent = new Intent(LocalActivity.this, AddActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
