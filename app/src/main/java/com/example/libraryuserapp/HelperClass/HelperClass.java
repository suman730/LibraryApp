package com.example.libraryuserapp.HelperClass;

public class HelperClass {
    String name,image,date,time,key;

    public HelperClass( ) {
    }

    public HelperClass(String name, String image, String date, String time, String key) {
        this.name = name;
        this.image = image;
        this.date = date;
        this.time = time;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
