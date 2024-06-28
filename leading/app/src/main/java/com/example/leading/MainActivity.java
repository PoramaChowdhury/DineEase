package com.example.leading;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        ImageView photo;
        TextView welcome,wish,forget_pass;
        EditText user,password;
        Button button_login,button_signup;

        photo =  findViewById(R.id.photo);
        user = findViewById(R.id.user_name);
        password = findViewById(R.id.password);
        button_login = findViewById(R.id.button_logIn);
        button_signup = findViewById(R.id.button_signUp);

       /* button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String userName = user.getText().toString();
              String Password = password.getText().toString();
            }
        });*/



        button_signup.setOnClickListener(new View.OnClickListener(){
         @Override
         public void onClick(View v) {
           Intent register;

             register = new Intent(MainActivity.this,Register.class);
             startActivity(register);
         }
        });





    }
}