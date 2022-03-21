package com.example.mobilapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class Registration extends AppCompatActivity {

    TextInputEditText textInputEditTextFullname, textInputEditTextUsername,textInputEditTextPassword,textInputEditTextEmail, textInputLayoutAddress, textInputLayoutPhoneNumber;
    Button registerButton;
    RegisterUser registerUser;
    TextView loginText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        registerUser = new RegisterUser();

        textInputEditTextFullname = findViewById(R.id.fullname);
        textInputLayoutAddress = findViewById(R.id.address);
        textInputEditTextEmail = findViewById(R.id.email);
        textInputLayoutPhoneNumber = findViewById(R.id.phoneNumber);
        textInputEditTextUsername = findViewById(R.id.username);
        textInputEditTextPassword = findViewById(R.id.password);
        loginText = findViewById(R.id.loginText);

        loginText.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });


        registerButton = findViewById(R.id.registerButton);




        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User(String.valueOf(textInputEditTextFullname.getText())
                        ,String.valueOf(textInputLayoutAddress.getText())
                        ,String.valueOf(textInputEditTextEmail.getText())
                        ,String.valueOf(textInputLayoutPhoneNumber.getText())
                        ,String.valueOf(textInputEditTextUsername.getText())
                        ,String.valueOf(textInputEditTextPassword.getText()));

                registerUser.putDataToDB(user);
            }
        });
    }
}