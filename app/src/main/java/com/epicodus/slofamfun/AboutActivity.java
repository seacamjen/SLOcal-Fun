package com.epicodus.slofamfun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.rateButton) Button mRateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        mRateButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mRateButton) {
            Toast.makeText(AboutActivity.this, "Thank you for the Rating", Toast.LENGTH_LONG).show();
        }
    }
}
