/*package com.example.leading;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DelateItem extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delate_item);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}*/

package com.example.leading;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class DelateItem extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText editTextName;
    private TextView textViewItemId;

    private TextView text_view_product_quantity;
    private TextView text_view_product_price;
    private ImageView imageViewProduct;
    private Button button_Delete;
    private Button button_Search;


    private DatabaseHelper databaseHelper;
    private byte[] bitmapToByteArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delate_item);

        editTextName = findViewById(R.id.text_view_product_name);
        textViewItemId = findViewById(R.id.text_view_product_id);
        text_view_product_quantity = findViewById(R.id.text_view_product_quantity);
        text_view_product_price = findViewById(R.id.text_view_product_price);
        imageViewProduct = findViewById(R.id.image_view_product);
        button_Search = findViewById(R.id.button_search);
        button_Delete = findViewById(R.id.button_delete);

        databaseHelper = new DatabaseHelper(this);

        button_Search.setOnClickListener(view -> searchProduct());
        button_Delete.setOnClickListener(view -> deleteProduct());
    }

    private void searchProduct() {
        String itemName = editTextName.getText().toString().trim();
        if (itemName.isEmpty()) {
            Toast.makeText(this, "Please enter a product name to search", Toast.LENGTH_SHORT).show();
            return;
        }

        Cursor cursor = databaseHelper.getProductByName(itemName);
        if (cursor != null && cursor.moveToFirst()) {
            int itemId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_ID));
            int price = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_Item_PRICE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_Item_Description));
            byte[] image = cursor.getBlob(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_Item_IMAGE_URI));

            text_view_product_quantity.setText(String.valueOf(text_view_product_quantity));
            text_view_product_price.setText(String.valueOf(text_view_product_price));
            textViewItemId.setText(" item ID: " + itemId);


            if (image != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                imageViewProduct.setImageBitmap(bitmap);
                bitmapToByteArray= image;
            }
            cursor.close();
        } else {
            Toast.makeText(this, "Product not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteProduct() {
        String itemName = editTextName.getText().toString().trim();

        databaseHelper.deleteProduct(itemName);
    }

    /*private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageViewProduct.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/
}
