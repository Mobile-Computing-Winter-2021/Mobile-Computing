package com.example.assignment5;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database( entities = {ScanData.class,IMUData.class},version = 7,exportSchema = false)
public abstract class RoomDb extends RoomDatabase {

    private static RoomDb database;
    private static String DATABASE_NAME="database";

    public synchronized static RoomDb getInstance(Context context){

        //database= Room.databaseBuilder(context.getApplicationContext(),RoomDb.class,DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        if(database == null){
            //initialize the database

            database= Room.databaseBuilder(context.getApplicationContext(), RoomDb.class,DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }

        return  database;
    }
    //create Dao
    public abstract ScanDao scanDao();
}
