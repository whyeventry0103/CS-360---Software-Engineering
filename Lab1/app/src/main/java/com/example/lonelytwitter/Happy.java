package com.example.lonelytwitter;

import org.xml.sax.DTDHandler;

import java.util.Date;

public class Happy extends Mood {

    public Happy(String current_mood, Date current_date) {
        super(current_mood, current_date);
    }

    public Happy() {
        super();
    }

    @Override
    public String currentMood() {
        return "Happy";
    }
}
