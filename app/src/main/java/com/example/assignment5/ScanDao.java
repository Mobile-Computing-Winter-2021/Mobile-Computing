package com.example.assignment5;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ScanDao {

    @Insert
    void insertscandata(ScanData scanData);

    //Query to get all data
    @Query("SELECT * FROM scanned_data")
    List<ScanData> getAllscannedValue();

    @Insert
    void insertimu(IMUData imuData);

    //Query to get all data
    @Query("SELECT * FROM IMU_table")
    List<IMUData> getAllimuValue();


}
