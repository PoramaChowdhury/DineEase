/* package com.example.leading;

import android.content.Context;
import android.content.Intent;
import android.database.AbstractCursor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdapter extends CursorAdapter {


    public ItemAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.item, parent, false);
    }

    */
/*@Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameTextView = view.findViewById(R.id.text_view_product_name);
        TextView priceTextView = view.findViewById(R.id.text_view_product_price);
        TextView descriptionTextView = view.findViewById(R.id.text_view_product_quantity);
        ImageView productImageView = view.findViewById(R.id.image_view_product);

        String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_Item_NAME));
        int price = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_Item_PRICE));
        String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_Item_Description));
        byte[] imageBytes = cursor.getBlob(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_Item_IMAGE_URI));


        nameTextView.setText(name);
        priceTextView.setText(String.valueOf(price));
        descriptionTextView.setText(description);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        productImageView.setImageBitmap(bitmap);

    }
*//*

   @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameTextView = view.findViewById(R.id.text_view_product_name);
        TextView priceTextView = view.findViewById(R.id.text_view_product_price);
        TextView descriptionTextView = view.findViewById(R.id.text_view_product_quantity);
        ImageView productImageView = view.findViewById(R.id.image_view_product);

        String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_Item_NAME));
        int price = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_Item_PRICE));
        String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_Item_Description));
        byte[] imageBytes = cursor.getBlob(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_Item_IMAGE_URI));

        nameTextView.setText(name);
        priceTextView.setText(String.valueOf(price));
        descriptionTextView.setText(description);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        productImageView.setImageBitmap(bitmap);
    }

    */
/*@Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        cursor.moveToPosition(position);
        String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_Item_NAME));
        double price = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_Item_PRICE));
        String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_Item_Description));
        byte[] imageBytes = cursor.getBlob(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_Item_IMAGE_URI));

        // Set text and image
        holder.nameTextView.setText(name);
        holder.priceTextView.setText(String.valueOf(price));
        holder.descriptionTextView.setText(description);
        if (imageBytes != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            holder.productImageView.setImageBitmap(bitmap);
        } else {
            // Handle case where no image is available (set default image or hide image view)
        }
    }*//*

}*/
package com.example.leading;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class ItemAdapter extends CursorAdapter {

    public ItemAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameTextView = view.findViewById(R.id.item_name);
        TextView priceTextView = view.findViewById(R.id.item_price);
        TextView descriptionTextView = view.findViewById(R.id.item_description);
        ImageView productImageView = view.findViewById(R.id.image);

        String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_Item_NAME));
        int price = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_Item_PRICE));
        String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_Item_Description));
        byte[] imageBytes = cursor.getBlob(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_Item_IMAGE_URI));

        nameTextView.setText(name);
        priceTextView.setText(String.valueOf(price));
        descriptionTextView.setText(description);

        if (imageBytes != null && imageBytes.length > 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            productImageView.setImageBitmap(bitmap);
        } else {
            productImageView.setImageResource(R.drawable.img_logo); // Use a default image if no image is available
        }
    }
}

