package com.example.shelter4u;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "register.db";
    public static final String TABLE_NAME = "registereruser";
    public static final String COL1 = "ID";
    public static final String COL2 = "username";
    public static final String COL3 = "password";

    public DatabaseHelper(Context context) {
        super(context, "register.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE registereruser(username text PRIMARY KEY,password text,email text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addUser(String user, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user);
        contentValues.put("password", password);
        long res = db.insert("registereruser", null, contentValues);
        db.close();
        return res;
    }

    public boolean checkUser(String username, String password) {

        String[] columns = {COL1};
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL2 + "=?" + "and " + COL3 + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.rawQuery("Select * from registereruser where username=?", new String[]{username});
        // Cursor cursor  = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        ;
        db.close();
        if (count > 0)
            return false; //user exist
        else {
            return true; //user dose'nt exist
        }
    }

    public boolean ReturnPasswordMatch(String username2, String password2) {
        SQLiteDatabase db = getReadableDatabase();
        String[] selectionArgs = {username2, password2};

        Cursor cursor= db.rawQuery("SELECT * FROM registereruser WHERE username='" + username2+ "'AND password ='" + password2 +"'", null);

        int count = cursor.getCount();
        if (count > 0)
            return false; //password match
        else {
            return true; //password dose'nt match
        }
    }
}