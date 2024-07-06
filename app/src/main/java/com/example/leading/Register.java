package com.example.leading;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView registerTextView;
        EditText usernameEditText,emailEditTest,passwordEditText,confirmEditText;
        Button loginButton;

        usernameEditText = findViewById(R.id.user_name);
        emailEditTest = findViewById(R.id.mail);
        passwordEditText = findViewById(R.id.password);
        confirmEditText = findViewById(R.id.confirm_pass);
        registerTextView = findViewById(R.id.register);
        loginButton = findViewById(R.id.button_logIn);

        loginButton.setOnClickListener(v->{
            String username = usernameEditText.getText().toString();
            String email = emailEditTest.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmEditText.getText().toString();


            if (password.equals(confirmPassword) && !password.isEmpty() && !username.isEmpty()){
                Toast.makeText(Register.this, "well done! Let me insert your info in DB!", Toast.LENGTH_SHORT).show();
                //connection with DB
                DatabaseHelper dbHelper = new DatabaseHelper(Register.this);
                boolean isInserted = dbHelper.insertUser(username, email, password);

                if (isInserted) {
                    Toast.makeText(Register.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(Register.this, "Passwords do not match or empty password or empty username!", Toast.LENGTH_SHORT).show();

            }

        });




    }
}