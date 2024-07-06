package com.example.leading;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Insert extends AppCompatActivity {

    private static final int REQUEST_IMAGE_PICK = 1;


    private TextView item_name;
    private TextView item_price;
    private TextView item_description;
    private ImageView image;
    private Button btn_choose_photo;
    private Button btn_insert_item;
    private DatabaseHelper databaseHelper;
    private byte[] imageByteArray;

    private ActivityResultLauncher<Intent> imagePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_insert);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        TextView Insert, item_name, item_price, item_description;
        ImageView selectedImageView;
        Button btn_choose_photo, btn_insert_item;

        selectedImageView = findViewById(R.id.image);
        Insert = findViewById(R.id.insert);
        item_name = findViewById(R.id.item_name);
        item_price = findViewById(R.id.item_price);
        item_description = findViewById(R.id.item_description);
        btn_insert_item = findViewById(R.id.btnInsert);
        btn_choose_photo = findViewById(R.id.btn_choose_image);

        databaseHelper = new DatabaseHelper(this);

        imagePickerLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                Uri imageUri = result.getData().getData();
                try {
                    Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    selectedImageView.setImageBitmap(imageBitmap);
                    imageByteArray = bitmapToByteArray(imageBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btn_choose_photo.setOnClickListener(view -> showImageSelectionDialog());

        btn_insert_item.setOnClickListener(view -> insertItem());

    }

    private void showImageSelectionDialog() {
        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");
        imagePickerLauncher.launch(pickIntent);
    }

    private byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void insertItem() {
        String name = item_name.getText().toString();
        String priceString = item_price.getText().toString();
        String description = item_description.getText().toString();

        if (name.isEmpty() || priceString.isEmpty() || description.isEmpty() || imageByteArray == null) {
            Toast.makeText(this, "Fill all fields and select an image", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int price = Integer.parseInt(priceString);
            databaseHelper.insertProduct(name, price, description, imageByteArray);
            Toast.makeText(this, "Product inserted successfully", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid price", Toast.LENGTH_SHORT).show();
        }
    }


}
