package com.example.leading;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        ImageView photoBeverage, photoMeat, photoAppetizer, photo_fav;
        Button Beverage, Meat_Item, Appetizer, Owners_fav;

        Beverage = findViewById(R.id.button_Beverage);
        Meat_Item = findViewById(R.id.button_Meat);
        Appetizer = findViewById(R.id.button_Appetizer);
        Owners_fav = findViewById(R.id.button_Favourite);

        Beverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openItemListActivity("Beverage");
            }
        });

        Meat_Item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openItemListActivity("Meat");
            }
        });

        Appetizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openItemListActivity("Appetizer");
            }
        });

        Owners_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openItemListActivity("Owner's Favourite");
            }
        });
    }

    private void openItemListActivity(String category) {
        Intent intent = new Intent(Menu.this, Item_list.class);
        intent.putExtra("CATEGORY", category);
        startActivity(intent);
    }
}
