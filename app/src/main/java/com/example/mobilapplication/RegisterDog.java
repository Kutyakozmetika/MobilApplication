package com.example.mobilapplication;
import android.os.Handler;
import android.os.Looper;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class RegisterDog  {
    protected void putDataToDB(DogDatas dogDatas) {

        if (!dogDatas.getIsAgressive().equals("") && !dogDatas.getFurType().equals("") && !dogDatas.getAge().equals("")
                && !dogDatas.getDogBreed().equals("") && !dogDatas.getUsersName().equals("")) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    String[] field = new String[6];
                    field[0] = "agresszivE";
                    field[1] = "szorzetTipus";
                    field[2] = "eletkor";
                    field[3] = "fajta";
                    field[4] = "tulajNev";

                    //Creating array for data
                    String[] data = new String[6];
                    data[0] = dogDatas.getIsAgressive();
                    data[1] = dogDatas.getFurType();
                    data[2] = dogDatas.getAge();
                    data[3] = dogDatas.getDogBreed();
                    data[4] = dogDatas.getUsersName();

                    PutData putData = new PutData("http://192.168.56.1/kutyakozmetikaphp/dogsignup.php", "POST", field, data);
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
        } else {
            //Toast.makeText(getApplicationContext(), "All fields required!", Toast.LENGTH_SHORT).show();
        }
    }
}
