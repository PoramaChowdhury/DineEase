package com.example.leading;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class Item_list extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private List<Item> itemList;
    private TextView categoryTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item_list);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        recyclerView = findViewById(R.id.item_list);
        categoryTitle = findViewById(R.id.category_title);

        String category = getIntent().getStringExtra("CATEGORY");
        categoryTitle.setText(category);

        itemList = new ArrayList<>();

        populateItems(category);

        itemAdapter = new ItemAdapter(this, itemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(itemAdapter);
    }

    private void populateItems(String category) {
        if (category.equals("Beverage")) {
            itemList.add(new Item("Coffee", "tk2", "Freshly brewed coffee"));
            itemList.add(new Item("Tea", "tk1.5", "Organic tea"));
        } else if (category.equals("Meat")) {
            itemList.add(new Item("Steak", "tk15", "Juicy steak"));
            itemList.add(new Item("Chicken", "tk10", "Grilled chicken"));
        } else if (category.equals("Appetizer")) {
            itemList.add(new Item("Spring Roll", "tk5", "Crispy spring roll"));
            itemList.add(new Item("Salad", "tk4", "Fresh salad"));
        } else if (category.equals("Owner's Favourite")) {
            itemList.add(new Item("Special Dish", "tk20", "Chef's special dish"));
        }
    }


}
