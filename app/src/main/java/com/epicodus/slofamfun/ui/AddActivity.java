package com.epicodus.slofamfun.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.epicodus.slofamfun.R;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Spinner cityDropdown = (Spinner)findViewById(R.id.citySpinner);
        String[] cities = new String[] {"Atascadero", "San Luis Obispo", "Paso Robles", "Morro Bay", "Avila Beach", "Pismo Beach", "Arroyo Grande", "Templeton", "Grover Beach", "Los Osos", "Cayucos", "Oceano", "Nipomo", "Santa Margarita"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cities);
        cityDropdown.setAdapter(adapter);

        Spinner strollerDropdown = (Spinner)findViewById(R.id.strollerSpinner);
        String[] options = new String[] {"No Strollers", "Single Strollers", "Double Strollers", "Single Jogger Strollers", "Double Jogger Strollers"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, options);
        strollerDropdown.setAdapter(adapter1);
    }
}
