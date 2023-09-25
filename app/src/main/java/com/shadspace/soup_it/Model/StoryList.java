package com.shadspace.soup_it.Model;

public class StoryList {
    public  String wrtnby,ttl,stry,tym,rimg;

    public  StoryList(String wrtnby,String ttl,String stry,String tym,String rimg) {
        this.wrtnby=wrtnby;
        this.ttl=ttl;
        this.stry=stry;
        this.tym=tym;
        this.rimg=rimg;



    }

    public String getWrtnby() {
        return wrtnby;
    }

    public String getTtl() {
        return ttl;
    }

    public String getStry() {
        return stry;
    }

    public String getTym() {
        return tym;
    }

    public String getRimg() {
        return rimg;
    }


}

