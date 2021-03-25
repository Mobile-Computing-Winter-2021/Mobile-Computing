package com.example.a4_sensors;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SensorDao {

    //Insert Query
    @Insert
    void insertLight(Light light);

   //Query to get all data
    @Query("SELECT * FROM TABLE_LIGHT")
    List<Light> getAllLightValue();

    @Insert
    void  insertacc(Accelerometer accelerometer);

    @Query("SELECT * FROM accelerometer_table")
    List<Accelerometer> getAllAccValue();

    @Insert
    void insertLinearAcc(LinearAccelerator linearAccelerator);

    @Query("SELECT * FROM linear_accelerator_table")
    List<LinearAccelerator> getAllLinearAccValue();

    @Insert
    void insertProximity(Proximity proximity);

    @Query("SELECT * FROM proximity_table")
    List<Proximity> getAllProximityValue();

    @Insert
    void insertLocation(GPSLocation gpsLocation);

    @Query("SELECT * FROM GPSLocation_Table")
    List<GPSLocation> getAllLocationValue();

    @Insert
    void insertTemperature(Temperature temperature);

    @Query("SELECT * FROM temperature_table")
    List<Temperature> getAllTempValue();
}
