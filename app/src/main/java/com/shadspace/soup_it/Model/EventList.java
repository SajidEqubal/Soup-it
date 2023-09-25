package com.shadspace.soup_it.Model;

public class EventList {




    public String title, topic, Sdate, Ldate, fee, venue ,video,availabe;


    public EventList(String title, String topic, String Sdate, String Ldate, String fee, String venue, String video, String availabe) {
        this.title = title;
        this.topic = topic;
        this.Sdate = Sdate;
        this.Ldate = Ldate;
        this.fee = fee;
        this.venue = venue;
        this.video = video;
        this.availabe = availabe;


    }

    public String getEventTitle() {
        return title;
    }

    public String getEventTopic() {
        return topic;
    }

    public String getEventStartDate() {
        return Sdate;
    }

    public String getEventLastDate() {
        return Ldate;
    }

    public String getEventFee() {
        return fee;
    }

    public String getEventVenue() {
        return venue;
    }

    public String getEventVideo() {
        return video;
    }

    public String getEventAvailabe() {
        return availabe;
    }

}
