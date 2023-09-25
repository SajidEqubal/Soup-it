package com.shadspace.soup_it.Model;

public class BlogList {


    public String category, ttl, content, wrtnby, tym, rimg;

    public BlogList(String category, String ttl, String content, String wrtnby, String tym, String rimg) {
        this.category = category;
        this.ttl = ttl;
        this.content = content;
        this.wrtnby = wrtnby;
        this.tym = tym;
        this.rimg = rimg;


    }

    public String getCategory1() {
        return category;
    }

    public String getTtl1() {
        return ttl;
    }

    public String getContent1() {
        return content;
    }


    public String getWrtnby1() {
        return wrtnby;
    }


    public String getTym1() {
        return tym;
    }

    public String getRimg1() {
        return rimg;
    }
}
