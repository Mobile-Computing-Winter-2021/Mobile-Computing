package com.example.a4_sensors;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "proximity_table")
public class Proximity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Proximity_Value")
    private String Proximity_Value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProximity_Value() {
        return Proximity_Value;
    }

    public void setProximity_Value(String proximity_Value) {
        Proximity_Value = proximity_Value;
    }
}
