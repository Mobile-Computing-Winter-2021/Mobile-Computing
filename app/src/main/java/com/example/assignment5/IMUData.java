package com.example.assignment5;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "IMU_table")
public class IMUData implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private  int id;

    @ColumnInfo(name="x_acc")
    private float x_acc;

    @ColumnInfo(name="y_acc")
    private float y_acc;



    @ColumnInfo(name="z_acc")
    private float z_acc;

    @ColumnInfo(name="x_gyr")
    private float x_gyr;

    @ColumnInfo(name="y_gyr")
    private float y_gyr;

    @ColumnInfo(name="z_gyr")
    private float z_gyr;

    @ColumnInfo(name="x_mag")
    private float x_mag;

    @ColumnInfo(name="y_mag")
    private float y_mag;

    @ColumnInfo(name="z_mag")
    private float z_mag;

    @ColumnInfo(name="time")
    private long time;

    @ColumnInfo(name="speed")
    private float speed;

    @ColumnInfo(name="direction")
    private String direction;

    @ColumnInfo(name="stepcount")
    private float stepcount;



    @ColumnInfo(name="Room_no")
    private int Room_no;

    public IMUData(int id, float x_acc, float y_acc, float z_acc, float x_gyr, float y_gyr, float z_gyr, float x_mag, float y_mag, float z_mag, long time, float speed, String direction, float stepcount,  int room_no) {
        this.id = id;
        this.x_acc = x_acc;
        this.y_acc = y_acc;
        this.z_acc = z_acc;
        this.x_gyr = x_gyr;
        this.y_gyr = y_gyr;
        this.z_gyr = z_gyr;
        this.x_mag = x_mag;
        this.y_mag = y_mag;
        this.z_mag = z_mag;
        this.time = time;
        this.speed = speed;
        this.direction = direction;
        this.stepcount = stepcount;
       // this.stepdetector = stepdetector;
        Room_no = room_no;
    }

    public IMUData() {

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

    public float getX_gyr() {
        return x_gyr;
    }

    public void setX_gyr(float x_gyr) {
        this.x_gyr = x_gyr;
    }

    public float getY_gyr() {
        return y_gyr;
    }

    public void setY_gyr(float y_gyr) {
        this.y_gyr = y_gyr;
    }

    public float getZ_gyr() {
        return z_gyr;
    }

    public void setZ_gyr(float z_gyr) {
        this.z_gyr = z_gyr;
    }

    public float getX_mag() {
        return x_mag;
    }

    public void setX_mag(float x_mag) {
        this.x_mag = x_mag;
    }

    public float getY_mag() {
        return y_mag;
    }

    public void setY_mag(float y_mag) {
        this.y_mag = y_mag;
    }

    public float getZ_mag() {
        return z_mag;
    }

    public void setZ_mag(float z_mag) {
        this.z_mag = z_mag;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public float getStepcount() {
        return stepcount;
    }

    public void setStepcount(float stepcount) {
        this.stepcount = stepcount;
    }



    public int getRoom_no() {
        return Room_no;
    }

    public void setRoom_no(int room_no) {
        Room_no = room_no;
    }
}
