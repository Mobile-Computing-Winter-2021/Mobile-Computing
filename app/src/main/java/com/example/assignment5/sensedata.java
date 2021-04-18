package com.example.assignment5;

public class sensedata {

    private  int roomno;
    private int rssi;

    public sensedata(int roomno, int rssi) {
        this.roomno = roomno;
        this.rssi = rssi;
    }

    public int getRoomno() {
        return roomno;
    }

    public void setRoomno(int roomno) {
        this.roomno = roomno;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }
}
