package com.usst.analyser.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LexicalAnalysis {
    /**
     * 1. 基本字 keyword
     * 2. 运算符 signs
     * 3. 界符 industry
     * <p>
     * 4. 常数 constant
     * 5. 标识符 identifier 用户定义的变量名，常数名，过程名
     */

    HashSet<String> keyword = new HashSet<>();
    HashMap<String, Signs> signs = new HashMap<>();
    HashMap<Integer, String> industry = new HashMap<Integer, String>();
    // http://www.cnblogs.com/nonefly/p/4688763.html
    String[] keywords = new String[]{
            "abstract", "boolean", "break", "byte", "case", "catch", "char", "class", "continue", "default", "do",
            "double", "else", "extends", "final", "finally", "float", "for", "if", "implements", "import", "instanceof",
            "int", "interface", "long", "native", "new", "package", "private", "protected", "public", "return", "short",
            "static", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void",
            "volatile", "while", "strictfp", "enum", "goto", "const", "assert"};
    // http://www.runoob.com/java/java-operators.html
    String[] operators = new String[]{
            "+", "-", "*", "/", "%", "++", "--", // 算术运算符
            "==", "!=", ">", "<", ">=", "<=", // 关系运算符
            "&", "|", "&", "~", "<<", ">>", ">>>", // 位运算符
            "&&", "||", "!", // 逻辑运算符
            "=", "+=", "-=", "*=", "/=", "%=", "<<=", ">>=", "&=", "^=", "|=", // 赋值运算符
            "?:", // 条件运算符
            "instanceof" // instanceof运算符
    };
    String[] industrys = new String[]{".", ",", ";", "'", "\"", "\\", "[", "]", "{", "}", ":", "(", ")", "//", "/*"};

    public LexicalAnalysis() {
        init();
    }

    // 将数组的值放入set或map中
    private void init() {
        Collections.addAll(keyword, keywords);
        signs.put("+", Signs.plus);
        signs.put("-", Signs.minus);
        signs.put("*", Signs.multiply);
        signs.put("/", Signs.divide);
        signs.put("%", Signs.remain);
        signs.put("++", Signs.plusOne);
        signs.put("--", Signs.minusOne);
        signs.put("==", Signs.equal);
        signs.put("!=", Signs.notEqual);
        signs.put(";", Signs.semicolon);
        signs.put("(", Signs.leftBracket);
        signs.put(")", Signs.rightBracket);
        signs.put("=", Signs.assign);
        signs.put(".", Signs.point);
        signs.put(">", Signs.moreThan);
        signs.put("<", Signs.lessThan);
        signs.put("{", Signs.leftCurlyBrace);
        signs.put("}", Signs.rightCurlyBrace);
        signs.put("[", Signs.leftSquareBracket);
        signs.put("]", Signs.rightSquareBracket);
        signs.put(",", Signs.comma);
        signs.put(":", Signs.colon);
        signs.put("\"", Signs.doubleQuotation);
        signs.put("'", Signs.singleQuotation);
        signs.put("_", Signs.underscore);
        signs.put("&&", Signs.and);
        signs.put("||", Signs.or);
    }

    // 获得TextField的值，分析里面的内容
    public List<Tuple> analyze(String text) {
        // 将其转为字符串数组（去掉空格）
        List<Tuple> tuples = new ArrayList<>();
        int length = text.length();
        int i = 0;
        while (i < length) {
            // 字符串
            if (text.charAt(i) == ' ') {
                i++;
            }
            if (Character.isLetter(text.charAt(i))) {
                StringBuilder sb = new StringBuilder();
                sb.append(text.charAt(i));
                i++;
                while (i < length) {
                    if (Character.isLetter(text.charAt(i))) {
                        sb.append(text.charAt(i));
                        i++;
                    } else {
                        break;
                    }
                }
                // 判断是关键字还是标识符
                if (keyword.contains(sb.toString())) {
                    tuples.add(new Tuple(Tag.KEYWORD, "keyword", sb.toString()));
                } else {
                    tuples.add(new Tuple(Tag.IDENTIFIER, "identifier", sb.toString()));
                }
            }
            if (i == length){
                break;
            }
            // 数字
            if (Character.isDigit(text.charAt(i))) {
                StringBuilder digit = new StringBuilder();
                digit.append(text.charAt(i));
                i++;
                while (i < length) {
                    if (Character.isDigit(text.charAt(i))) {
                        digit.append(text.charAt(i));
                        i++;
                    } else {
                        break;
                    }
                }
                // 加入tuples
                tuples.add(new Tuple(Tag.CONSTANT, "constant", digit.toString()));
            }
            if (i == length){
                break;
            }
            // 判断是否为非字母字符
            if (!Character.isDigit(text.charAt(i)) && !Character.isLetter(text.charAt(i)) && text.charAt(i) != ' ') {
                StringBuilder sign = new StringBuilder();
                sign.append(text.charAt(i));
                i++;
                while (i < length) {
                    if (!Character.isDigit(text.charAt(i)) && !Character.isLetter(text.charAt(i)) && text.charAt(i) != ' ') {
                        if (text.charAt(i) != '=' && text.charAt(i) != '+' && text.charAt(i) != '-'
                                && text.charAt(i) != '&' && text.charAt(i) != '|') {
                            break;
                        }
                        sign.append(text.charAt(i));
                        i++;
                    } else {
                        break;
                    }
                }
                if (signs.containsKey(sign.toString())) {
                    tuples.add(new Tuple(signs.get(sign.toString()).getNumber(), signs.get(sign.toString()).getName(), sign.toString()));
                } else {
                    System.out.println("err input: " + sign.toString());
                }
            }
        }
        return tuples;
    }

    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(new File("F:\\principleofcompiling\\input")));
            String temp;
            while ((temp = in.readLine()) != null) {
                text.append(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        LexicalAnalysis lexicalAnalysis = new LexicalAnalysis();
        List<Tuple> tuples = lexicalAnalysis.analyze(text.toString());
        if (tuples != null) {
            for (Tuple aTuple : tuples) {
                System.out.println(aTuple.getNumber() + ", " + aTuple.getType() + ", " + aTuple.getName());
            }
        }
    }
}
