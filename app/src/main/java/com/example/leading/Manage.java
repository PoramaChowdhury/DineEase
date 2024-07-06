package com.example.leading;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Manage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manage);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        Button btn_dlt, btn_up;

        btn_dlt = findViewById(R.id.btn_dlt);
        btn_up = findViewById(R.id.btn_up);

        btn_dlt.setOnClickListener(v -> {
            Intent intent = new Intent(Manage.this, DelateItem.class);
            startActivity(intent);
        });

        btn_up.setOnClickListener(v -> {
            Intent intent = new Intent(Manage.this, UpdateItem.class);
            startActivity(intent);
        });
    }
}