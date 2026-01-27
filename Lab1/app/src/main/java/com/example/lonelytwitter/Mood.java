package com.example.lonelytwitter;

import java.util.Date;

public abstract class Mood {

    private String current_mood;
    private Date current_date;

    public Mood(String current_mood, Date current_date) {
        this.current_mood = current_mood;
        this.current_date = current_date;
    }

    public Mood(Date current_date) {
        this.current_date = current_date;
        this.current_mood = "";
    }

    public Mood() {
        this.current_date = new Date();
        this.current_mood = "nothing";
    }

    public Date getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(Date current_date) {
        this.current_date = current_date;
    }

    public String getCurrent_mood() {
        return current_mood;
    }

    public void setCurrent_mood(String current_mood) {
        this.current_mood = current_mood;
    }

    public abstract  String currentMood();
}
