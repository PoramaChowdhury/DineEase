/*package com.example.leading;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Item_List extends AppCompatActivity {
    private ListView listViewProducts;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        listViewProducts = findViewById(R.id.list_view_products);
        Button Manage = findViewById(R.id.button_manage);

        databaseHelper = new DatabaseHelper(this);

        displayProducts();

        Manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleManage();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        displayProducts();
    }

    private void displayProducts() {
        Cursor cursor = databaseHelper.getAllProducts();
        ItemAdapter adapter = new ItemAdapter(this, cursor, 0);
        listViewProducts.setAdapter(adapter);
    }

    private void handleManage() {

        Intent intent = new Intent(Item_List.this, Manage.class); // Assuming HomeActivity is the activity after login
        startActivity(intent);
    }


}*/

package com.example.leading;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Item_List extends AppCompatActivity {
    private ListView listViewProducts;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        listViewProducts = findViewById(R.id.list_view_products);
        Button Manage = findViewById(R.id.button_manage);

        databaseHelper = new DatabaseHelper(this);

        displayProducts();

        Manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleManage();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayProducts();
    }

    private void displayProducts() {
        Cursor cursor = databaseHelper.getAllProducts();
        if (cursor != null && cursor.getCount() > 0) {
            ItemAdapter adapter = new ItemAdapter(this, cursor, 0);
            listViewProducts.setAdapter(adapter);
        } else {
            Toast.makeText(this, "No products available", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleManage() {
        Intent intent = new Intent(Item_List.this, Manage.class);
        startActivity(intent);
    }
}

