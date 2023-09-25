package com.shadspace.soup_it.Model;

public class PopularBlogList {

    public String category, ttl, content, wrtnby, tym, rimg;

    public PopularBlogList(String category, String ttl, String content, String wrtnby, String tym, String rimg) {
        this.category = category;
        this.ttl = ttl;
        this.content = content;
        this.wrtnby = wrtnby;
        this.tym = tym;
        this.rimg = rimg;


    }

    public String getCategory2() {
        return category;
    }

    public String getTtl2() {
        return ttl;
    }

    public String getContent2() {
        return content;
    }


    public String getWrtnby2() {
        return wrtnby;
    }


    public String getTym2() {
        return tym;
    }

    public String getRimg2() {
        return rimg;
    }
}
