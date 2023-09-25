package com.shadspace.soup_it.Model;

public class YourStoryList {


    public String title, type, story, name, phone, date, status;


    public YourStoryList(String title, String type, String story, String name, String phone, String date, String status) {
        this.title = title;
        this.type = type;
        this.story = story;
        this.name = name;
        this.phone = phone;
        this.date = date;
        this.status = status;


    }

    public String getTitlee() {
        return title;
    }

    public String getTypee() {
        return type;
    }

    public String getStoryy() {
        return story;
    }


    public String getNamee() {
        return name;
    }


    public String getPhonee() {
        return phone;
    }

    public String getDatee() {
        return date;
    }

    public String getStatuss() {
        return status;
    }
}
