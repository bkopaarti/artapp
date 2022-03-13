package com.artapp.models;

public class vehicle {
    public String vhno;
    public String fromlocation;
    public String tolocation;
    public String vhimg;

    public String getVhno() {
        return vhno;
    }

    public void setVhno(String vhno) {
        this.vhno = vhno;
    }

    public String getFromlocation() {
        return fromlocation;
    }

    public void setFromlocation(String fromlocation) {
        this.fromlocation = fromlocation;
    }

    public String getTolocation() {
        return tolocation;
    }

    public void setTolocation(String tolocation) {
        this.tolocation = tolocation;
    }

    public String getVhimg() {
        return vhimg;
    }

    public void setVhimg(String vhimg) {
        this.vhimg = vhimg;
    }

    public vehicle(String vhno, String fromlocation, String tolocation) {
        this.vhno = vhno;
        this.fromlocation = fromlocation;
        this.tolocation = tolocation;
    }
}
