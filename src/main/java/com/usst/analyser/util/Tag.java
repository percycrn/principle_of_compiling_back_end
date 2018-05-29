package com.usst.analyser.util;

class Tag {
    final static int
            KEYWORD = 1, // 关键字
            IDENTIFIER = 2, // 标识符
            CONSTANT = 3, // 常数
            PLUS = 4, // 加
            MINUS = 5, // 减
            MULTIPLY = 6, // 乘
            DIVIDE = 7, // 除
            REMAIN = 8, // 取余
            PLUSONE = 9, // 加一
            MINUSONE = 10, // 减一
            EQUAL = 11, // 相等 ==
            NOTEQUAL = 12, // 不相等 !=
            SEMICOLON = 13, // 分号
            LEFTBRACKET = 14, // 左括号
            RIGHTBRACKET = 15, // 右括号
            ASSIGN = 16, // = 赋值
            POINT = 17, // .
            MOREThAN = 18, // 大于
            LESSThAN = 19, // 小于
            LEFTCURLYBRACE = 20, // 左大括号
            RIGHTCURLYBRACE = 21, // 右大括号
            LEFTSQUAREBRACKET = 22, // 左方括号
            RIGHTSQUAREBRACKER = 23, // 右方括号
            COMMA = 24, // 逗号
            COLON = 25, // 冒号
            DOUBLEQUOTATION = 26, // 双引号
            SINGLEQUOTATION = 27, // 单引号
            UNDERSCORE = 28, // 下划线
            OR = 29, // ||
            AND = 30; // &&
}