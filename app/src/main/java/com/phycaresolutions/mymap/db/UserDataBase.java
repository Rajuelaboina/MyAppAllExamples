package com.phycaresolutions.mymap.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.phycaresolutions.mymap.ItemClass;

public class UserDataBase extends SQLiteOpenHelper {
    public  static final String DATABASE_NAME = "user";
    private final String TABLE_NAME = "user";
    private final String NAME = "name";
    private final String ID = "id";
    private final String PASSWORD = "password";
    public static final int DB_VERSION = 2;

    public UserDataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL("CREATE TABLE " + TABLE_NAME + "("+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ NAME + " TEXT, "+ PASSWORD + " Text " + ")" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }
    public long insertData(ItemClass itemClass){
        ContentValues cv = new ContentValues();
        cv.put(NAME,"RRRRRRRRRRRRRRR");
        cv.put(PASSWORD,"PAPAPAPPA");
        SQLiteDatabase db = getReadableDatabase();
        long id = db.insert(TABLE_NAME,null,cv);
        return id;
    }
}
