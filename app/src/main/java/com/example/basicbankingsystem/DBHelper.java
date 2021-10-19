package com.example.basicbankingsystem;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;



public class DBHelper extends SQLiteOpenHelper {

    public static final String database="Customers.DB";
    public DBHelper(@Nullable Context context) {
        super(context,database,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table users (accountid integer primary key ,username text ,mail TEXT,balance double,phno text,address text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists users");
        onCreate(db);
    }

    public boolean insertData(int accountid,String username,String mail,double balance,String phno,String address)
    {
            SQLiteDatabase db=this.getWritableDatabase();
             ContentValues contentValues=new ContentValues();
             contentValues.put("accountid",accountid);
             contentValues.put("username",username);
             contentValues.put("mail",mail);
             contentValues.put("balance",balance);
             contentValues.put("phno",phno);
            contentValues.put("address",address);
             db.insert("users",null,contentValues);
             return true;
    }

    public Cursor getData(int accountid)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res =db.rawQuery("Select * from users where accountid = "+accountid,null);
        res.moveToFirst();
        return res;
    }
    public Cursor getallData()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("Select * from users",null);
        return res;
    }
    public Cursor getBalance(int accountid)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("Select balance from users where accountid = "+accountid,null);
        res.moveToFirst();
        return res;
    }
    public boolean updateBalance(int accountid,float amount)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("update users set balance = "+amount+ " where accountid = "+accountid);
        return true;
    }




}
