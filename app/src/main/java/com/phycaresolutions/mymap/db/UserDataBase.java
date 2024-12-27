package com.phycaresolutions.mymap.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.phycaresolutions.mymap.Db_Item;

import java.util.ArrayList;
import java.util.List;

public class UserDataBase extends SQLiteOpenHelper {
    public  static final String DATABASE_NAME = "user";
    private final String TABLE_NAME = "user";
    private final String NAME = "name";
    private final String ID = "id";
    private final String PASSWORD = "password";
    private final String IMAGE = "image";
    public static final int DB_VERSION = 2;

    public UserDataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
     //db.execSQL("CREATE TABLE " + TABLE_NAME + "("+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ NAME + " TEXT, "+ PASSWORD + " Text " + ")" );
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(id integer primary key, name text, password text,image blob)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }
    public long insertData(Db_Item itemClass){
        ContentValues cv = new ContentValues();
        cv.put(NAME,itemClass.getName());
        cv.put(PASSWORD,itemClass.getPassword());
        cv.put(IMAGE,itemClass.getInputData());
        SQLiteDatabase db = getReadableDatabase();
        long id = db.insert(TABLE_NAME,null,cv);
        return id;

    }
    public boolean getIsUserExist(String name,String password){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT name, password FROM " + TABLE_NAME + " WHERE NAME = '"+ name +"' AND PASSWORD= '"+password +"'", null);
       boolean bb = false;
        if (cursor.moveToFirst()){
            do {
               if (name.equals(cursor.getString(0)) && password.equals(cursor.getString(1))){
                   bb = true;
                }else {
                    bb = false;
                }
            }while (cursor.moveToNext());
        }
        return bb;
    }
    public  List<String> getUserData(){
        SQLiteDatabase db = getReadableDatabase();
        String str = "";
        List<String> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()){
            do {
                list.add(cursor.getString(0));
                list.add(cursor.getString(1));
                list.add(cursor.getString(2));

            }while (cursor.moveToNext());
        }
        return list;
    }
    public  List<Db_Item> getUserData2(){
        SQLiteDatabase db = getReadableDatabase();
        List<Db_Item> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()){
            do {
                list.add(new Db_Item(cursor.getString(1),cursor.getString(2),cursor.getBlob(3)));
            }while (cursor.moveToNext());
        }
        return list;
    }
}
