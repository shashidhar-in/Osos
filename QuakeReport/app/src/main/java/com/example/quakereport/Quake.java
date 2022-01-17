package com.example.quakereport;

public class Quake {
    private String Qmag;
    private String Qplace;
    private String Qtime;

    public Quake(String mag,String place,String time) {
        Qmag=mag;
        Qplace=place;
        Qtime=time;


    }
    public String getQmag(){
        return Qmag;

    }
    public String getQplace(){
        return Qplace;

    }
    public String getQtime(){
        return Qtime;
    }
}
