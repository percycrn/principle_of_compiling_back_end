package com.usst.analyser.util;

class Signs {
    private int number;
    private String name;

    private Signs(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    static final Signs
            plus = new Signs(Tag.PLUS, "plus"),
            minus = new Signs(Tag.MINUS, "minus"),
            multiply = new Signs(Tag.MULTIPLY, "multiply"),
            divide = new Signs(Tag.DIVIDE, "divide"),
            remain = new Signs(Tag.REMAIN, "remain"),
            plusOne = new Signs(Tag.PLUSONE, "plus one"),
            minusOne = new Signs(Tag.MINUSONE, "minus one"),
            equal = new Signs(Tag.EQUAL, "equal"),
            notEqual = new Signs(Tag.NOTEQUAL, "not equal"),
            semicolon = new Signs(Tag.SEMICOLON, "semicolon"),
            leftBracket = new Signs(Tag.LEFTBRACKET, "left bracket"),
            rightBracket = new Signs(Tag.RIGHTBRACKET, "right bracket"),
            assign = new Signs(Tag.ASSIGN, "assign"),
            point = new Signs(Tag.POINT, "point"),
            moreThan = new Signs(Tag.MOREThAN, "more than"),
            lessThan = new Signs(Tag.LESSThAN, "less than"),
            leftCurlyBrace = new Signs(Tag.LEFTCURLYBRACE, "left big bracket"),
            rightCurlyBrace = new Signs(Tag.RIGHTCURLYBRACE, "right big bracket"),
            leftSquareBracket = new Signs(Tag.LEFTSQUAREBRACKET, "left square bracket"),
            rightSquareBracket = new Signs(Tag.RIGHTSQUAREBRACKER, "right square bracket"),
            comma = new Signs(Tag.COMMA, "comma"),
            colon = new Signs(Tag.COLON, "colon"),
            doubleQuotation = new Signs(Tag.DOUBLEQUOTATION, "double quotation"),
            singleQuotation = new Signs(Tag.SINGLEQUOTATION, "single quotation"),
            underscore = new Signs(Tag.UNDERSCORE, "underscore"),
            or = new Signs(Tag.OR, "or"),
            and = new Signs(Tag.AND, "and");
}
