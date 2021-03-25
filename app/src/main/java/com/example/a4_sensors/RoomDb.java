package com.example.a4_sensors;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database( entities = {Light.class, Accelerometer.class, LinearAccelerator.class, Proximity.class, GPSLocation.class, Temperature.class},version = 11,exportSchema = false)
public abstract class RoomDb extends RoomDatabase {

    private static RoomDb database;
    private static String DATABASE_NAME="database";

    public synchronized static RoomDb getInstance(Context context){
        if(database == null){
            //initialize the database

            database= Room.databaseBuilder(context.getApplicationContext(),
                    RoomDb.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        //return database
        return  database;
    }
    //create Dao
    public abstract SensorDao sensorDao();
}
