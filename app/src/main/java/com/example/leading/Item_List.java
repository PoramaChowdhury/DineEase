/*
package com.example.leading;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Item_List extends AppCompatActivity {

    ArrayList<item> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        RecyclerView recyclerView = findViewById(R.id.item_list);
        TextView categoryTitle = findViewById(R.id.category_title);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String category = getIntent().getStringExtra("CATEGORY");
        assert category != null;
        populateItems(category);




        RecyclerItemAdapter adapter = new RecyclerItemAdapter(this,itemList);
        recyclerView.setAdapter(adapter);

    }

    private void populateItems(String category) {


        switch (category) {
            case "Beverage":
                itemList.add(new item(R.drawable.img_logo, "Coffee", "2", "Fresh"));
                itemList.add(new item(R.drawable.img_logo, "Tea", "1.5", "Organic tea"));
                break;
            case "Meat":
                itemList.add(new item(R.drawable.img_logo, "Steak", "15", "Juicy steak"));
                itemList.add(new item(R.drawable.img_logo, "Chicken", "10", "Grilled chicken"));
                break;
            case "Appetizer":
                itemList.add(new item(R.drawable.img_logo, "Spring Roll", "tk5", "Crispy spring roll"));
                itemList.add(new item(R.drawable.img_logo, "Salad", "tk4", "Fresh salad"));
                break;
            case "Owner's Favourite":
                itemList.add(new item(R.drawable.img_logo, "Special Dish", "tk20", "Chef's special dish"));
                break;
        }


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
        Button manageAll = findViewById(R.id.button_manage);
        databaseHelper = new DatabaseHelper(this);

        displayProducts();

        manageAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleManage();
            }
        });

    }
    private void handleManage() {

        Intent intent = new Intent(Item_List.this, Manage.class); // Assuming HomeActivity is the activity after login
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the displayed products
        displayProducts();
    }

    private void displayProducts() {
        Cursor cursor = databaseHelper.getAllProducts();
        ItemAdapter adapter = new ItemAdapter(this, cursor, 0);
        listViewProducts.setAdapter(adapter);
    }

    /*private void handleUpdate() {
        // Logic for updating a product
        Intent intent = new Intent(ViewProductActivity.this, UpdateProductActivity.class); // Assuming HomeActivity is the activity after login
        startActivity(intent);
    }
    //
    private void handleDelete() {
        Intent intent = new Intent(ViewProductActivity.this, DeleteProductActivity.class); // Assuming HomeActivity is the activity after login
        startActivity(intent);
        Toast.makeText(this, "Delete button clicked", Toast.LENGTH_SHORT).show();
    }*/
}