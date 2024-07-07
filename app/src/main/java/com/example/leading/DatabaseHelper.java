package com.example.leading;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Test_DB";

    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_REGISTER = "Register";
    public static final String COL_ID = "Id";
    public static final String COL_USERNAME = "user_name";
    public static final String COL_EMAIL = "email_Id";
    public static final String COL_PASSWORD = "password";

    public static final String TABLE_Insertion = "Insertion";
    public static final String COL_Item_NAME = "ItemName";
    public static final String COL_Item_PRICE = "Price";
    public static final String COL_Item_Description = "Description";
    public static final String COL_Item_IMAGE_URI = "itemImageUri";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_REGISTER);
        // db.execSQL("DROP TABLE IF EXISTS " + TABLE_Insertion);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_REGISTER + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USERNAME + " TEXT, " +
                COL_EMAIL + " TEXT, " +
                COL_PASSWORD + " TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_Insertion + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_Item_NAME + " TEXT, " +
                COL_Item_PRICE + " REAL, " +
                COL_Item_Description + " TEXT, " +
                COL_Item_IMAGE_URI + " BLOB)");

    }

    public boolean insertUser(String username, String emailId, String password) {

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USERNAME, username);
        contentValues.put(COL_EMAIL, emailId);
        contentValues.put(COL_PASSWORD, password);

        long result = db.insert(TABLE_REGISTER, null, contentValues);

        return result != -1;
    }

    public boolean checkUserName(String username, String password) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_REGISTER + " WHERE " + COL_USERNAME + " = ? AND " + COL_PASSWORD + " = ?", new String[]{username, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public void insertProduct(String name, int price, String description, byte[] imageByteArray) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_Item_NAME, name);
        values.put(COL_Item_PRICE, price);
        values.put(COL_Item_Description, description);
        values.put(COL_Item_IMAGE_URI, imageByteArray);
        db.insert(TABLE_Insertion, null, values);
        db.close();
    }

    public Cursor getAllProducts() {

        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(" SELECT * FROM " + TABLE_Insertion, null);
    }

    public Cursor getProductByName(String productName) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_Insertion + " WHERE " + COL_Item_NAME + " = ?", new String[]{productName});
    }

    public void updateProduct(int itemId, String itemName, int price, String itemDescription, byte[] productImageByteArray) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(COL_Item_NAME, itemName);
        values.put(COL_Item_PRICE, price);
        values.put(COL_Item_Description, itemDescription);
        values.put(COL_Item_IMAGE_URI, productImageByteArray);

        db.update(TABLE_Insertion, values, COL_ID + " = ?", new String[]{String.valueOf(itemId)});
        db.close();
    }

    public void deleteProduct(String itemName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Insertion, COL_Item_NAME + " = ?", new String[]{itemName});
        db.close();

    }


}
