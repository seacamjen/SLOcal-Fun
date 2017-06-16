package com.epicodus.slofamfun.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.epicodus.slofamfun.Constants;
import com.epicodus.slofamfun.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String cityChosen;

    @Bind(R.id.titleView) TextView mTitleView;
    @Bind(R.id.localButton) Button mLocalButton;
    @Bind(R.id.aboutButton) Button mAboutButton;
    @Bind(R.id.logoutButton) Button mLogoutButton;
    @Bind(R.id.searchButton) Button mSearchButton;
    @Bind(R.id.searchInput) Spinner mSearchInput;
    @Bind(R.id.chooseCity) TextView mChooseCity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        Typeface funfont = Typeface.createFromAsset(getAssets(), "fonts/drawfont.ttf");
        mTitleView.setTypeface(funfont);
        mChooseCity.setTypeface(funfont);

        Spinner cityDropdown = (Spinner)findViewById(R.id.searchInput);
        String[] cities = new String[] {"Atascadero", "San Luis Obispo", "Paso Robles", "Morro Bay", "Avila Beach", "Pismo Beach", "Arroyo Grande", "Templeton", "Grover Beach", "Los Osos", "Cayucos", "Oceano", "Nipomo", "Santa Margarita"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cities);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        cityDropdown.setAdapter(adapter);

        cityDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cityChosen = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mLocalButton.setOnClickListener(this);
        mAboutButton.setOnClickListener(this);
        mLogoutButton.setOnClickListener(this);
        mSearchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mLocalButton) {
            if(!(cityChosen).equals("")) {
                addToSharedPreferences(cityChosen);
            }
            Intent intent = new Intent(MainActivity.this, LocalUiActivity.class);
            startActivity(intent);
        }
        if (v == mAboutButton) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }
        if (v == mSearchButton) {
            addToSharedPreferences(cityChosen);
            Intent intent = new Intent(MainActivity.this, YelpActivity.class);
            startActivity(intent);
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
