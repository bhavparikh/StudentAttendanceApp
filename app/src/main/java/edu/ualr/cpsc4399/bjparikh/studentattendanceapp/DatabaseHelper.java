package edu.ualr.cpsc4399.bjparikh.studentattendanceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "credentials.db";
    private static final String TABLE_NAME = "credentials";
    private static final String COLUMN_ID = "Id";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_UNAME = "UName";
    private static final String COLUMN_PASS = "Pass";
    private static final String COLUMN_EMAIL = "Email";
    SQLiteDatabase database;
    private static final String TABLE_CREATE = "Create table credentials (Id integer primary key not null , " +
            "Name text not null , Uname text not null , Pass text not null , Email text not null)" ;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void insertCredentials(Credentials credentials){
        database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        String query = "select * from credentials";
        Cursor cursor = database.rawQuery(query, null);
        int count = cursor.getCount();

        contentValues.put(COLUMN_ID, count);
        contentValues.put(COLUMN_NAME, credentials.getName());
        contentValues.put(COLUMN_UNAME, credentials.getUname());
        contentValues.put(COLUMN_PASS, credentials.getPassw());
        contentValues.put(COLUMN_EMAIL, credentials.getEmail());

        database.insert(TABLE_NAME, null, contentValues);
        database.close();
    }

    public String searchPass(String uname){
        database = this.getReadableDatabase();
        String query = "select Uname, Pass from " + TABLE_NAME;
        Cursor cursor = database.rawQuery(query, null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);
                if(a.equals(uname)){
                    b = cursor.getString(1);
                    break;
                }
            }while (cursor.moveToNext());
        }

        return b;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.database = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        database.execSQL(query);
        this.onCreate(database);
    }
}
