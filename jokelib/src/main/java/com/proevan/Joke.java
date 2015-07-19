package com.proevan;

/**
 * The object model for the data we are sending through endpoints
 */
public class Joke {

    private String text;

    public Joke(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}