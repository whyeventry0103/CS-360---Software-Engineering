package com.example.lonelytwitter;

import java.util.Date;

public class Sad extends Mood{

    private Date date;
    private final String current_mood;
    ;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Sad(Date date, String current_mood1) {
        super(current_mood1);
        this.date = date;
        this.current_mood = current_mood1;
    }

    public Sad(String current_mood1) {
        super(current_mood1);
        this.date = new Date();
        this.current_mood = current_mood1;
    }

    public String getMood() {
        return current_mood;
    }
}
