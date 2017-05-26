package com.epicodus.slofamfun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.yelpButton) Button mYelpButton;
    @Bind(R.id.localButton) Button mLocalButton;
    @Bind(R.id.aboutButton) Button mAboutButton;
    @Bind(R.id.homeButton) Button mHomeButton;
    @Bind(R.id.searchButton) Button mSearchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mYelpButton.setOnClickListener(this);
        mLocalButton.setOnClickListener(this);
        mAboutButton.setOnClickListener(this);
        mHomeButton.setOnClickListener(this);
        mSearchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mYelpButton) {
            Intent intent = new Intent(MainActivity.this, YelpActivity.class);
            startActivity(intent);
        } else if (v == mLocalButton) {
            Intent intent = new Intent(MainActivity.this, LocalActivity.class);
            startActivity(intent);
        } else if (v == mAboutButton) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        } else if (v == mHomeButton) {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (v == mSearchButton) {
            String search = mSearchButton.getText().toString();
            Intent intent = new Intent(MainActivity.this, LocalActivity.class);
            intent.putExtra("search", search);
            startActivity(intent);
        }
    }
}
