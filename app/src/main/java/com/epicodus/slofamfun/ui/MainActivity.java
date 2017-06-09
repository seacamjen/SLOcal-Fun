package com.epicodus.slofamfun.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.epicodus.slofamfun.Constants;
import com.epicodus.slofamfun.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Bind(R.id.titleView) TextView mTitleView;
//    @Bind(R.id.yelpButton) Button mYelpButton;
    @Bind(R.id.localButton) Button mLocalButton;
    @Bind(R.id.aboutButton) Button mAboutButton;
    @Bind(R.id.logoutButton) Button mLogoutButton;
//    @Bind(R.id.homeButton) Button mHomeButton;
    @Bind(R.id.searchButton) Button mSearchButton;
    @Bind(R.id.searchInput) EditText mSearchInput;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        Typeface funfont = Typeface.createFromAsset(getAssets(), "fonts/drawfont.ttf");
        mTitleView.setTypeface(funfont);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

//        awesomeValidation.addValidation(this, R.id.searchInput, "^\\d{5}(?:[-\\s]\\d{4})?$", R.string.searcherror);

//        mYelpButton.setOnClickListener(this);
        mLocalButton.setOnClickListener(this);
        mAboutButton.setOnClickListener(this);
        mLogoutButton.setOnClickListener(this);
//        mHomeButton.setOnClickListener(this);
        mSearchButton.setOnClickListener(this);
    }

    private void submitForm() {
        if(awesomeValidation.validate()) {
//            Toast.makeText(this, "Validation Successful", Toast.LENGTH_SHORT).show();

            String city = mSearchInput.getText().toString();
            if(!(city).equals("")) {
                addToSharedPreferences(city);
            }
            Intent intent = new Intent(MainActivity.this, YelpActivity.class);
            intent.putExtra("city", city);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
//        if (v == mYelpButton) {
//            Intent intent = new Intent(MainActivity.this, YelpActivity.class);
//            startActivity(intent);
//        }
        if (v == mLocalButton) {
            String city = mSearchInput.getText().toString();
            if(!(city).equals("")) {
                addToSharedPreferences(city);
            }
            Intent intent = new Intent(MainActivity.this, LocalUiActivity.class);
            startActivity(intent);
        }
        if (v == mAboutButton) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }
//        if (v == mHomeButton) {
//            Intent intent = new Intent(MainActivity.this, MainActivity.class);
//            startActivity(intent);
//        }
        if (v == mSearchButton) {
            submitForm();
        }
        if (v == mLogoutButton) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MainActivity.this, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }

    private void addToSharedPreferences(String city) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, city).apply();
    }
}
