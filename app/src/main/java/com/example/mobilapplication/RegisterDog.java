package com.example.mobilapplication;

import android.os.Handler;
import android.os.Looper;


import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class RegisterDog  {
    protected void putDataToDB(DogDatas dogDatas) {

        if (!dogDatas.getIsAgressive().equals("") && !dogDatas.getAge().equals("")
                && !dogDatas.getDogBreed().equals("") && !dogDatas.getUsersName().equals("")) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    String[] field = new String[4];
                    field[0] = "agresszivE";
                    field[1] = "eletkor";
                    field[2] = "fajta";
                    field[3] = "tulajNev";

                    //Creating array for data
                    String[] data = new String[4];
                    data[0] = dogDatas.getIsAgressive();
                    data[1] = dogDatas.getAge();
                    data[2] = dogDatas.getDogBreed();
                    data[3] = dogDatas.getUsersName();

                    PutData putData = new PutData("http://192.168.100.56/kutyakozmetikaphp/dogsignup.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                        }
                    }
                }
            });
        }
    }
}
