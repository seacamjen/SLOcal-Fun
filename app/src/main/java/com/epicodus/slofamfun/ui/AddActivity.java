package com.epicodus.slofamfun.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.epicodus.slofamfun.R;
import com.google.firebase.database.DatabaseReference;
import com.epicodus.slofamfun.models.LocalActivity;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.newActivityName) EditText mNewActivityName;
    @Bind(R.id.newActivityAddress) EditText mNewActivityAdress;
    @Bind(R.id.newActivityPhone) EditText mNewActivityPhone;
    @Bind(R.id.newActivityWebsite) EditText mNewActivityWebsite;
    @Bind(R.id.newActivityComments) EditText mNewActivityComments;
    @Bind(R.id.newActivityAgeRange) EditText mNewAgeRange;
    @Bind(R.id.newActivityImage) EditText mNewActivityImage;
    @Bind(R.id.citySpinner) Spinner mCitySpinner;
    @Bind(R.id.strollerSpinner) Spinner mStrollerSpinner;
    @Bind(R.id.addNewActivity) Button mAddNewActivity;

    private DatabaseReference mActiveRef;
    private String cityChosen;
    private String strollerChosen;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mActiveRef = FirebaseDatabase
                .getInstance()
                .getReference();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);

        Spinner cityDropdown = (Spinner)findViewById(R.id.citySpinner);
        String[] cities = new String[] {"Atascadero", "San Luis Obispo", "Paso Robles", "Morro Bay", "Avila Beach", "Pismo Beach", "Arroyo Grande", "Templeton", "Grover Beach", "Los Osos", "Cayucos", "Oceano", "Nipomo", "Santa Margarita"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cities);
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


        Spinner strollerDropdown = (Spinner)findViewById(R.id.strollerSpinner);
        String[] options = new String[] {"No Strollers", "Single Strollers", "Double Strollers", "Single Jogger Strollers", "Double Jogger Strollers"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, options);
        strollerDropdown.setAdapter(adapter1);

        strollerDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strollerChosen = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mAddNewActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mAddNewActivity){
            String name = mNewActivityName.getText().toString();
            String phone = mNewActivityPhone.getText().toString();
            String address = mNewActivityAdress.getText().toString();
            String website = mNewActivityWebsite.getText().toString();
            String comments = mNewActivityComments.getText().toString();
            String age = mNewAgeRange.getText().toString();
            String image = mNewActivityImage.getText().toString();
            String stroller = strollerChosen;

            LocalActivity localActivity = new LocalActivity(name, image, website, address, phone, stroller, comments, age);
            saveLocalAcivityToFirebase(localActivity);

            Intent intent = new Intent(AddActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void saveLocalAcivityToFirebase(LocalActivity localActivity) {
        mActiveRef.child(cityChosen).push().setValue(localActivity);
    }
}
