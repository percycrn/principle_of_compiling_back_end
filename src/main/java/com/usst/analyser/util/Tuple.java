package com.usst.analyser.util;

public class Tuple {
    private int number;
    private String type;
    private String name;

    public Tuple(int number, String type, String name) {
        this.number = number;
        this.type = type;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
