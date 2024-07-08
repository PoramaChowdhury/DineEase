package com.example.leading;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
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


    private EditText item_name;
    private EditText item_price;
    private EditText item_description;
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




        image = findViewById(R.id.iv_selected_image);

        item_name = findViewById(R.id.et_item_name);
        item_price = findViewById(R.id.et_item_price);
        item_description = findViewById(R.id.et_item_description);
        btn_insert_item = findViewById(R.id.btn_insert_product);
        btn_choose_photo = findViewById(R.id.btn_select_image);

        databaseHelper = new DatabaseHelper(this);

        imagePickerLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                Uri imageUri = result.getData().getData();
                try {
                    Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    image.setImageBitmap(imageBitmap);
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
        int priceString = Integer.parseInt(item_price.getText().toString());
        String description = item_description.getText().toString();

        if (name.isEmpty() ||  imageByteArray == null) {
            Toast.makeText(this, "Fill all fields and select an image", Toast.LENGTH_SHORT).show();
            return;
        }


        databaseHelper.insertProduct(name,priceString, description, imageByteArray);
        Toast.makeText(this, "Product inserted successfully", Toast.LENGTH_SHORT).show();
    }


}
