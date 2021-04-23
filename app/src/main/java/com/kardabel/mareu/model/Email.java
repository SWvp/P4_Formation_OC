package com.kardabel.mareu.model;

public class Email {

    private final String name;

    private final String url;

    public Email(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
