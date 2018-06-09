package com.usst.analyser.util;

class Signs {
    private int number;
    private String name;

    private Signs(int number, String name) {
        this.number = number;
        this.name = name;
    }

    int getNumber() {
        return number;
    }

    String getName() {
        return name;
    }

    static final Signs
            newLine = new Signs(Tag.NEWLINT,"newline"),
            plus = new Signs(Tag.PLUS, "plus"),
            minus = new Signs(Tag.MINUS, "minus"),
            multiply = new Signs(Tag.MULTIPLY, "multiply"),
            divide = new Signs(Tag.DIVIDE, "divide"),
            equal = new Signs(Tag.EQUAL, "equal"),
            leftBracket = new Signs(Tag.LEFTBRACKET, "left bracket"),
            rightBracket = new Signs(Tag.RIGHTBRACKET, "right bracket"),
            semicolon = new Signs(Tag.SEMICOLON, "semicolon"),
            assign = new Signs(Tag.ASSIGN, "assign"),
            moreThan = new Signs(Tag.MOREThAN, "more than"),
            lessThan = new Signs(Tag.LESSThAN, "less than"),
            comma = new Signs(Tag.COMMA, "comma"),
            period = new Signs(Tag.POINT, "period"),
            moreOrEqual = new Signs(Tag.MOREOREQUAL, "more or equal"),
            lessOrEqual = new Signs(Tag.LESSOREQUAL, "less or equal"),
            wellName = new Signs(Tag.WELLNAME, "well name"),
            remain = new Signs(Tag.REMAIN, "remain"),
            plusOne = new Signs(Tag.PLUSONE, "plus one"),
            minusOne = new Signs(Tag.MINUSONE, "minus one"),
            notEqual = new Signs(Tag.NOTEQUAL, "not equal"),
            leftCurlyBrace = new Signs(Tag.LEFTCURLYBRACE, "left big bracket"),
            rightCurlyBrace = new Signs(Tag.RIGHTCURLYBRACE, "right big bracket"),
            leftSquareBracket = new Signs(Tag.LEFTSQUAREBRACKET, "left square bracket"),
            rightSquareBracket = new Signs(Tag.RIGHTSQUAREBRACKER, "right square bracket"),
            colon = new Signs(Tag.COLON, "colon"),
            doubleQuotation = new Signs(Tag.DOUBLEQUOTATION, "double quotation"),
            singleQuotation = new Signs(Tag.SINGLEQUOTATION, "single quotation"),
            underscore = new Signs(Tag.UNDERSCORE, "underscore"),
            or = new Signs(Tag.OR, "or"),
            and = new Signs(Tag.AND, "and"),
            doubleSlash = new Signs(Tag.DOUBLESLASH, "double slash");
}
