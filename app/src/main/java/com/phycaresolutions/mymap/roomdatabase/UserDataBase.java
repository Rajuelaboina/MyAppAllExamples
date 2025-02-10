package com.phycaresolutions.mymap.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {PersonDetails.class},version = 1)
public abstract class UserDataBase extends RoomDatabase {
    public abstract QueryServiceCall personDao();

    static UserDataBase userDataBase;
    public static UserDataBase getInstance(Context context){
        if (userDataBase==null){
           userDataBase = Room.databaseBuilder(context,UserDataBase.class,"personDetails")
                   .fallbackToDestructiveMigration()
                   .build();
        }
        return userDataBase;
    }

    // Executor for background database operations
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);

}
