package com.example.lab6_ph35654;

public class item_DSsinhvien {
    private String name;
    private String location;
    private String coSo;



    public item_DSsinhvien(String name, String location, String coSo) {
        this.name = name;
        this.location = location;
        this.coSo = coSo;
    }

    public item_DSsinhvien() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCoSo() {
        return coSo;
    }

    public void setCoSo(String coSo) {
        this.coSo = coSo;
    }

}
