package com.example.leading;

import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Item_list extends AppCompatActivity {

    private List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item_list);

        RecyclerView recyclerView = findViewById(R.id.item_list);
        TextView categoryTitle = findViewById(R.id.category_title);

        String category = getIntent().getStringExtra("CATEGORY");
        categoryTitle.setText(category);

        itemList = new ArrayList<>();
        assert category != null;
        populateItems(category);

        ItemAdapter itemAdapter = new ItemAdapter(this, itemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(itemAdapter);
    }

    private void populateItems(String category) {
        if (category.equals("Beverage")) {
            itemList.add(new Item(R.drawable.img_background,"Coffee", "tk2", "Freshly brewed coffee"));
            itemList.add(new Item(R.drawable.img_background,"Tea", "tk1.5", "Organic tea"));
        } else if (category.equals("Meat")) {
            itemList.add(new Item(R.drawable.img_background,"Steak", "tk15", "Juicy steak"));
            itemList.add(new Item(R.drawable.img_background,"Chicken", "tk10", "Grilled chicken"));
        } else if (category.equals("Appetizer")) {
            itemList.add(new Item(R.drawable.img_background,"Spring Roll", "tk5", "Crispy spring roll"));
            itemList.add(new Item(R.drawable.img_background,"Salad", "tk4", "Fresh salad"));
        } else if (category.equals("Owner's Favourite")) {
            itemList.add(new Item(R.drawable.img_background,"Special Dish", "tk20", "Chef's special dish"));
        }
    }
}
