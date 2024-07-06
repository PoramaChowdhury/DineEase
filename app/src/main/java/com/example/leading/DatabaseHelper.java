package com.example.leading;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper   {

    public static final String DATABASE_NAME = "Test_DB";

    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_REGISTER = "Register";
    public static final String COL_ID = "Id";
    public static final String COL_USERNAME = "user_name";
    public static final String COL_EMAIL = "emailId";
    public static final String COL_PASSWORD = "password" ;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_REGISTER);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_REGISTER + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_EMAIL + " TEXT, " +
                COL_USERNAME + " TEXT, " +
                COL_PASSWORD + " TEXT)");
    }



    public boolean insertUser(String username,String emailId, String password) {

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USERNAME,username);
        contentValues.put(COL_EMAIL,emailId);
        contentValues.put(COL_PASSWORD,password);

        long result = db.insert(TABLE_REGISTER,null,contentValues);

        return result != -1;
    }

    public boolean checkUserName(String username, String password) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_REGISTER + " WHERE " + COL_USERNAME + " = ? AND " + COL_PASSWORD + " = ?",new String[] {username,password});
        boolean exists =  cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
}
