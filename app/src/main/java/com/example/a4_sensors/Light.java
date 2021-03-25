package com.example.a4_sensors;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "table_Light")
public class Light implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private  int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ColumnInfo (name = "light_value")
    private float light_value;

    @ColumnInfo(name="time")
    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public float getLight_value() {
        return light_value;
    }

    public void setLight_value(float light_value) {
        this.light_value = light_value;
    }
}
