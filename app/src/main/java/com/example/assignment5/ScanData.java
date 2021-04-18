package com.example.assignment5;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName="Scanned_Data")
public class ScanData implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private  int id;

    @ColumnInfo(name="RoomNo")
    private int RoomNo;

    @ColumnInfo(name="SSID")
    private String SSID;

    @ColumnInfo(name="RSSI")
    private int RSSI;

    public  ScanData(){

    }
    public ScanData(int id, int roomNo, String SSID, int RSSI) {
        this.id = id;
        RoomNo = roomNo;
        this.SSID = SSID;
        this.RSSI = RSSI;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomNo() {
        return RoomNo;
    }

    public void setRoomNo(int roomNo) {
        RoomNo = roomNo;
    }

    public String getSSID() {
        return SSID;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public int getRSSI() {
        return RSSI;
    }

    public void setRSSI(int RSSI) {
        this.RSSI = RSSI;
    }
}
