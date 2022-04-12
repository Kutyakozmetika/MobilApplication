package com.example.mobilapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment extends AppCompatActivity {

    Spinner daySpinner, hourSpinner,serviceSpinner;
    Button appointment;
    String username;
    ImageButton back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        daySpinner = findViewById(R.id.Day);
        hourSpinner = findViewById(R.id.Hour);
        appointment = findViewById(R.id.Appointment);

        serviceSpinner = (Spinner) findViewById(R.id.Service);

        getJSON("http://192.168.56.1/kutyakozmetikaphp/szolgaltatasFetch.php");


        Intent intent = getIntent();
        if(intent.getExtras().isEmpty())
        {
            username = "Felhasználó";
        }else{
            username = intent.getExtras().getString("username");
        }



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
                String serviceName = serviceSpinner.getSelectedItem().toString();

                if(!day.equals("")) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[4];
                            field[0] = "foglalasNapja";
                            field[1] = "foglalasOraja";
                            field[2] = "felhasznalonev";
                            field[3] = "szolgaltatasNev";
                            //Creating array for data
                            String[] data = new String[4];
                            data[0] = day;
                            data[1] = hour;
                            data[2] = username;
                            data[3] = serviceName;
                            PutData putData = new PutData("http://192.168.56.1/kutyakozmetikaphp/order.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Order Success")){
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), UserMenu.class);
                                        intent.putExtra("username", username);
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
    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] betoltottAdat = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            betoltottAdat[i] = obj.getString("szolgaltatasNev");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, betoltottAdat);
        serviceSpinner.setAdapter(adapter);
    }
}