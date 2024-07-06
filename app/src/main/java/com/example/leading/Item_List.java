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
                itemList.add(new item(R.drawable.img_logo, "Coffee", "tk2", "Freshly brewed coffee"));
                itemList.add(new item(R.drawable.img_logo, "Tea", "tk1.5", "Organic tea"));
                break;
            case "Meat":
                itemList.add(new item(R.drawable.img_logo, "Steak", "tk15", "Juicy steak"));
                itemList.add(new item(R.drawable.img_logo, "Chicken", "tk10", "Grilled chicken"));
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
}