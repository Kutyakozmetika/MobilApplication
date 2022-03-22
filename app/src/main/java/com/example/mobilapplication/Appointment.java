package com.example.mobilapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment extends AppCompatActivity {

    Spinner daySpinner, hourSpinner;
    Button appointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        daySpinner = findViewById(R.id.Day);
        hourSpinner = findViewById(R.id.Hour);

        appointment = findViewById(R.id.Appointment);

        String[] days = new String[]{"Hétfő", "Kedd", "Szerda", "Csütörtök", "Péntek", "Szombat", "Vasárnap"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, days);
        daySpinner.setAdapter(adapter);

        String[] hours = new String[]{"10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00"};
        final ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, hours);
        hourSpinner.setAdapter(adapter1);

        appointment.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String day = daySpinner.getSelectedItem().toString();
                String hour = hourSpinner.getSelectedItem().toString();


                if(!day.equals("")) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "foglalasNapja";
                            field[1] = "foglalasOraja";
                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = day;
                            data[1] = hour;
                            PutData putData = new PutData("http://10.0.11.107/kutyakozmetikaphp/order.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Order Success")){
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                    Log.i("PutData", result);
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(), "All fields required!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}