package com.example.boxandgo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBClass extends SQLiteOpenHelper {

    /*
   User Registration Data
    */
    private String TABLE_NAME = "Info";
    private static final String NAME_COL = "name";
    private static final String USERNAME_COL = "username";
    private static final String USERID_COL = "userid";
    private static final String DATE_COL = "date_of_birth";
    private static final String EMAIL_COL = "user_email";
    private static final String PASSWORD_COL = "password";

    public DBClass(Context context, String DATABASE_NAME) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + USERID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT, "
                + USERNAME_COL + " TEXT, "
                + EMAIL_COL + " TEXT, "
                + DATE_COL + " TEXT, "
                + PASSWORD_COL + " BLOB)";

        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "TABLE_NAME");

    }


    public void addInfo(String name, String username, String d_o_b, String email, byte[] password){
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put(USERNAME_COL, username);
        values.put(NAME_COL, name);
        values.put(DATE_COL, d_o_b);
        values.put(EMAIL_COL, email);
        values.put(PASSWORD_COL, password);

        //insert values in table
        db.insert(TABLE_NAME,null, values);
        db.close();
    }

    public String selectVal(String fieldName, String TABLE_NAME, String condition){
        String query = "SELECT " + fieldName + " FROM " + TABLE_NAME + " WHERE " + condition;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor extract = db.rawQuery(query,null);
        extract.moveToFirst();

        String str = extract.getString(0);
        return str;
    }

    public String selectAll(String fieldName, String TABLE_NAME){
        //String c="username=\"shw123\"";
        String query = "SELECT " + fieldName + " FROM " + TABLE_NAME ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor extract = db.rawQuery(query,null);
        extract.moveToFirst();

        String str = extract.getString(0);
        return str;
    }

    public byte[] selectPasscode( String fieldName, String TABLE_NAME, String condition ){
        String query = "SELECT " + fieldName + " FROM " + TABLE_NAME + " WHERE " + condition;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery(query,null);
        byte[] val=null;
        if(cur!=null){
            cur.moveToFirst();
            val = cur.getBlob(0);

        }
        return val;
    }



}
