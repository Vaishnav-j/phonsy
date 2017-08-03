package com.example.vaishnavj.phonesy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vaishnav J on 04-03-2017.
 */

public class ContactInfoDB extends SQLiteOpenHelper{


    ContactInfoDB(Context c){
        super(c,"DataBase.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String cTable ="create table if not exists DataBase ( id int primary key, phone varchar(10) )";
        db.execSQL(cTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean insertContact (int id,String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("phone", phone);
        db.insert("DataBase", null, contentValues);
        return true;
    }

    public String[] getData(SQLiteDatabase db) {

        String[] data=new String[5];
        Cursor res = db.rawQuery("select * from DataBase", null);
        res.moveToFirst();
        data[0]=res.getString(1);
        res.moveToNext();
        data[1]=res.getString(1);
        res.moveToNext();
        data[2]=res.getString(1);
        res.moveToNext();
        data[3]=res.getString(1);
        res.moveToNext();
        data[4]=res.getString(1);
        return data;
    }

    public void delete(SQLiteDatabase db,int id)
    {
        String SQL_DELETE_DATA = "DELETE FROM DataBase where id = "+id;
        db.execSQL(SQL_DELETE_DATA);
    }
    public void update(SQLiteDatabase db,int id,String newnum)
    {
        String SQL_UPDATE_DATA = "UPDATE DataBase set phone = "+newnum+ " where id = "+id;
        db.execSQL(SQL_UPDATE_DATA);
    }

}
