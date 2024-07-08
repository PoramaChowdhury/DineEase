
package com.example.leading;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        Button btnInsertProduct, btnMenu,btnManage;

        btnInsertProduct = findViewById(R.id.btn_insert_product);
        btnMenu = findViewById(R.id.btn_item_list);
        btnManage = findViewById(R.id.btn_updatedlt);

        btnInsertProduct.setOnClickListener(v -> {
            Intent intent = new Intent(AdminPage.this, Insert.class);
            startActivity(intent);
        });

        btnMenu.setOnClickListener(v -> {
            Intent intent = new Intent(AdminPage.this,Item_List.class);
            startActivity(intent);
        });

        btnManage.setOnClickListener(v -> {
            Intent intent = new Intent(AdminPage.this, Manage.class);
            startActivity(intent);
        });
    }
}
