package com.example.lonelytwitter;

import java.util.Date;

public abstract class Mood {

    private String current_mood;

    public Mood(String current_mood) {
        this.current_mood = current_mood;
    }

    public String getCurrent_mood() {
        return current_mood;
    }

    public void setCurrent_mood(String current_mood) {
        this.current_mood = current_mood;
    }
}
