package com.example.mobilapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;

public class DogRegistration extends AppCompatActivity {
    TextInputEditText textInputEditTextAgressive,textInputEditTextAge,textInputEditTextBreed,textInputEditTextUsersName;
    Button registerButton;
    ImageButton imageButton;
    String username;
    RegisterDog registerDog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_registration);
        registerDog = new RegisterDog();
        textInputEditTextAgressive = findViewById(R.id.agressive);
        textInputEditTextAge = findViewById(R.id.age);
        textInputEditTextBreed = findViewById(R.id.breed);
        textInputEditTextUsersName = findViewById(R.id.usersname);

        registerButton = findViewById(R.id.registerButton);
        registerButton .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DogDatas dogDatas = new DogDatas(String.valueOf(textInputEditTextAgressive.getText())
                        ,String.valueOf(textInputEditTextAge.getText())
                        ,String.valueOf(textInputEditTextBreed.getText())
                        ,String.valueOf(textInputEditTextUsersName.getText()));
                registerDog.putDataToDB(dogDatas);
                Intent intent = new Intent(getApplicationContext(), UserMenu.class);
                startActivity(intent);
                finish();
            }
        });

        imageButton = findViewById(R.id.imageButton);
        Intent intent = getIntent();
        if(intent.getExtras().isEmpty())
        {
            username = "Felhasználó";
        }else{
            username = intent.getExtras().getString("username");
        }
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserMenu.class);
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
            }
        });

    }

}