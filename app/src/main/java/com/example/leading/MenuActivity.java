/*
package com.example.leading;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ImageView photoBeverage, photoMeat, photoAppetizer, photo_fav;
        Button Beverage, Meat_Item, Appetizer, Owners_fav;

        photoBeverage = findViewById(R.id.photoBeverage);
        photoMeat = findViewById(R.id.photoMeat);
        photoAppetizer = findViewById(R.id.photoAppetizer);
        photo_fav = findViewById(R.id.photo_fav);

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
        Intent intent = new Intent(MenuActivity.this, Item_List.class);
        intent.putExtra("CATEGORY", category);
        startActivity(intent);
    }
}*/
