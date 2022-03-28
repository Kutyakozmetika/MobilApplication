package com.example.mobilapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserMenu extends AppCompatActivity {

    Button AppointmentButton,ownappointmentButton;
    String username;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
        AppointmentButton = findViewById(R.id.appointmentBt);
        ownappointmentButton = findViewById(R.id.MyAppointmentsBt);
        Intent intent = getIntent();
        if(intent.getExtras().isEmpty())
        {
            username = "Felhasználó";
        }else{
            username = intent.getExtras().getString("Username");
        }

        AppointmentButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Appointment.class);
                username = intent.getStringExtra("Username");
                intent.putExtra("Username", username);
                startActivity(intent);
                finish();
            }
        });

    }
}