package com.example.individual_assig;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    private Button register;
    private EditText user;
    private EditText email;
    private EditText password;

    private SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        shared = getSharedPreferences("User_Preference", Context.MODE_PRIVATE);

        register= findViewById(R.id.register);
        user = findViewById(R.id.user);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = user.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String Pass = password.getText().toString().trim();

                if(name.isEmpty() || Email.isEmpty() || Pass.isEmpty())
                {
                    Toast.makeText(Registration.this, "Please enter all particulars",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences.Editor edit = shared.edit();
                edit.putString("Username", name);
                edit.putString("Email", Email);
                edit.putString("Password", Pass);
                edit.apply();

                Toast.makeText(Registration.this, "You have succesfully register an account",
                        Toast.LENGTH_SHORT).show();


            }
        });

    }
}