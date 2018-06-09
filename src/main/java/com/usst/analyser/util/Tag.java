package com.usst.analyser.util;

class Tag {
    final static int
            NEWLINT = 0, // 换行
            KEYWORD = 1, // 关键字
            IDENTIFIER = 2, // 标识符
            CONSTANT = 3, // 常数
            PLUS = 4, // 加
            MINUS = 5, // 减
            MULTIPLY = 6, // 乘
            DIVIDE = 7, // 除
            EQUAL = 8, // = 相等(PL0)
            WELLNAME = 9, // #号
            MOREOREQUAL = 10, // 大于等于
            LESSOREQUAL = 11, // 小于等于
            MOREThAN = 12, // 大于
            LESSThAN = 13, // 小于
            LEFTBRACKET = 14, // 左括号
            RIGHTBRACKET = 15, // 右括号
            COMMA = 17, // 逗号
            SEMICOLON = 18, // 分号
            ASSIGN = 19, // := 赋值(PL0)
            POINT = 20, // .
            DOUBLESLASH = 21, // 双斜杠

            REMAIN = 21, // 取余
            PLUSONE = 22, // 加一
            MINUSONE = 23, // 减一
            NOTEQUAL = 24, // 不相等 !=
            LEFTCURLYBRACE = 25, // 左大括号
            RIGHTCURLYBRACE = 26, // 右大括号
            LEFTSQUAREBRACKET = 27, // 左方括号
            RIGHTSQUAREBRACKER = 28, // 右方括号
            COLON = 29, // 冒号
            DOUBLEQUOTATION = 30, // 双引号
            SINGLEQUOTATION = 31, // 单引号
            UNDERSCORE = 32, // 下划线
            OR = 33, // ||
            AND = 34; // &&
}