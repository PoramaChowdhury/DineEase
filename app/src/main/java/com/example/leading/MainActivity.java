package com.example.leading;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etUsername, etPassword;
        Button btnLogin, btnSignUp;

        etUsername = findViewById(R.id.user_name);
        etPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.button_logIn);
        btnSignUp = findViewById(R.id.button_signUp);


        btnLogin.setOnClickListener(v -> {

            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this, "fill the data", Toast.LENGTH_SHORT).show();
            }
            else {
                if (username.equals("admin") && password.equals("admin")) {
                    Intent intent = new Intent(MainActivity.this, AdminPage.class);
                    startActivity(intent);
                }
                else {
                    DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this);
                    boolean result = dbHelper.checkUserName(username, password);

                    if (result) {
                        Toast.makeText(MainActivity.this, "Welcome!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, MenuActivity.class);  // Changed to Menu.class
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid credentials!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

        btnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Register.class);
            startActivity(intent);
        });
    }
}
