package com.usst.analyser.util;

public class Tuple {
    private int number;
    private String type;
    private String content;

    Tuple(int number, String type, String content) {
        this.number = number;
        this.type = type;
        this.content = content;
    }

    public int getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
