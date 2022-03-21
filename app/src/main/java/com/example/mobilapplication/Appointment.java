package com.example.mobilapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Appointment extends AppCompatActivity {

    Spinner daySpinner, hourSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        daySpinner = findViewById(R.id.Day);
        hourSpinner = findViewById(R.id.Hour);

        String[] days = new String[]{"Hétfő", "Kedd", "Szerda", "Csütörtök", "Péntek", "Szombat", "Vasárnap"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, days);
        daySpinner.setAdapter(adapter);

        String[] hours = new String[]{"10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00"};
        final ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, hours);
        hourSpinner.setAdapter(adapter1);
    }
}