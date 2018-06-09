package com.usst.analyser.util;

public class Error {
    private int line; // 错误所在行数
    private String type; // 错误类型

    public Error(int line, String type) {
        this.line = line;
        this.type = type;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
