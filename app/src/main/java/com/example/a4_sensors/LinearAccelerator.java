package com.example.a4_sensors;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "linear_accelerator_table")
public class LinearAccelerator {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="x_linear")
    private String x_linear;

    @ColumnInfo(name="y_linear")
    private String y_linear;

    @ColumnInfo(name="z_linear")
    private String z_linear;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getX_linear() {
        return x_linear;
    }

    public void setX_linear(String x_linear) {
        this.x_linear = x_linear;
    }

    public String getY_linear() {
        return y_linear;
    }

    public void setY_linear(String y_linear) {
        this.y_linear = y_linear;
    }

    public String getZ_linear() {
        return z_linear;
    }

    public void setZ_linear(String z_linear) {
        this.z_linear = z_linear;
    }
}
