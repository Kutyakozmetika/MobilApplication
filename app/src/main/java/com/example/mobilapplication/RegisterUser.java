package com.example.mobilapplication;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;


import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class RegisterUser {

    protected void putDataToDB(User user){

        if(!user.getFullname().equals("") && !user.getAddress().equals("") && !user.getEmail().equals("") && !user.getPhoneNumber().equals("") && !user.getUsername().equals("") && !user.getPassword().equals("")  ) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    String[] field = new String[6];
                    field[0] = "nev";
                    field[1] = "cim";
                    field[2] = "email";
                    field[3] = "telefonszam";
                    field[4] = "felhasznalonev";
                    field[5] = "jelszo";
                    //Creating array for data
                    String[] data = new String[6];
                    data[0] = user.getFullname();
                    data[1] = user.getAddress();
                    data[2] = user.getEmail();
                    data[3] = user.getPhoneNumber();
                    data[4] = user.getUsername();
                    data[5] = user.getPassword();
                    PutData putData = new PutData("http://10.0.11.106/kutyakozmetikaphp/signup.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            //if(result.equals("Sign Up Success")){
                                //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        /*Intent intent = new Intent(getApplicationContext(), Login.class);
                                        startActivity(intent);
                                        finish();*/
                            //}else{
                                //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                            //}
                            //Log.i("PutData", result);
                        }
                    }
                }
            });
        }
        else{
            //Toast.makeText(getApplicationContext(), "All fields required!", Toast.LENGTH_SHORT).show();
        }
    }


}
