package com.example.a4_sensors;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "accelerometer_table")
public class Accelerometer implements Serializable {

    @PrimaryKey (autoGenerate = true)
    private  int id;

    @ColumnInfo(name="x_acc")
    private float x_acc;

    @ColumnInfo(name="y_acc")
    private float y_acc;

    @ColumnInfo(name="z_acc")
    private float z_acc;

    @ColumnInfo(name="time")
    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getX_acc() {
        return x_acc;
    }

    public void setX_acc(float x_acc) {
        this.x_acc = x_acc;
    }

    public float getY_acc() {
        return y_acc;
    }

    public void setY_acc(float y_acc) {
        this.y_acc = y_acc;
    }

    public float getZ_acc() {
        return z_acc;
    }

    public void setZ_acc(float z_acc) {
        this.z_acc = z_acc;
    }
}
