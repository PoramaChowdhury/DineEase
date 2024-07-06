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

public class UpdateItem extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText editTextName;
    private EditText editTextPrice;
    private EditText editTextDescription;
    private ImageView imageViewProduct;
    private Button button_Update;
    private Button buttonImageSelect;
    private Button button_Search;
    private TextView textViewItemId;

    private DatabaseHelper databaseHelper;
    private byte[] productImageByteArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);

        editTextName = findViewById(R.id.edit_text_product_name);
        editTextPrice = findViewById(R.id.edit_text_product_price);
        editTextDescription = findViewById(R.id.edit_text_product_description);
        imageViewProduct = findViewById(R.id.image_view_product);
        button_Update = findViewById(R.id.button_update);
        buttonImageSelect = findViewById(R.id.button_select_image);
        button_Search = findViewById(R.id.button_search);
        textViewItemId = findViewById(R.id.text_view_product_id);

        databaseHelper = new DatabaseHelper(this);

        button_Search.setOnClickListener(view -> searchProduct());
        buttonImageSelect.setOnClickListener(view -> selectImage());
        button_Update.setOnClickListener(view -> updateProduct());
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

            editTextPrice.setText(String.valueOf(price));
            editTextDescription.setText(description);
            textViewItemId.setText("Item ID: " + itemId);

            if (image != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                imageViewProduct.setImageBitmap(bitmap);
                productImageByteArray = image;
            }
            cursor.close();
        } else {
            Toast.makeText(this, "Product not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void selectImage() {
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

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
                productImageByteArray = byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateProduct() {
        String itemName = editTextName.getText().toString().trim();
        String itemPrice = editTextPrice.getText().toString().trim();
        String itemDescription = editTextDescription.getText().toString().trim();

        if (itemName.isEmpty() || itemPrice.isEmpty() || itemDescription.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int price = Integer.parseInt(itemPrice);

        String itemIdText = textViewItemId.getText().toString();
        int itemId = Integer.parseInt(itemIdText.replaceAll("\\D+", ""));

        databaseHelper.updateProduct(itemId, itemName, price, itemDescription, productImageByteArray);
        Toast.makeText(this, "Product updated successfully", Toast.LENGTH_SHORT).show();
    }
}
