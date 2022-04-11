package com.example.mobilapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserMenu extends AppCompatActivity {

    Button AppointmentButton,ownappointmentButton, dogregisterButton;
    String username;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
        AppointmentButton = findViewById(R.id.appointmentBt);
        ownappointmentButton = findViewById(R.id.MyAppointmentsBt);
        dogregisterButton = findViewById(R.id.dogRegisterBt);
        Intent intent = getIntent();
        if(intent.getExtras().isEmpty())
        {
            username = "Felhasználó";
        }else{
            username = intent.getExtras().getString("username");
        }

        AppointmentButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Appointment.class);
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
            }
        });
        dogregisterButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v2) {
                Intent intent = new Intent(getApplicationContext(), DogRegistration.class);
                startActivity(intent);
                finish();
            }
        });


    }
}