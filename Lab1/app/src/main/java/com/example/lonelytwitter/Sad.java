package com.example.lonelytwitter;

import java.util.Date;

public class Sad extends Mood{
    public Sad(String current_mood, Date current_date) {
        super(current_mood, current_date);
    }

    public Sad() {
        super();
    }

    @Override
    public String currentMood() {
        return "Sad";
    }
}
