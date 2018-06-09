package com.usst.analyser.util;

import java.util.ArrayList;
import java.util.List;

public class SyntaxAnalysis {

    private static int lines = 1;
    private static List<Error> errors = new ArrayList<>();
    private static List<Tuple> tuples;
    private static int beginEndFlag = 0;

    // 主程序
    public static List<Error> program(List<Tuple> tuples) {
        // 将tuples
        init();
        SyntaxAnalysis.tuples = tuples;
        subProgram();
        return errors;
    }

    private static void init() {
        lines = 1;
        errors.clear();
        beginEndFlag = 0;
    }

    // 子程序
    // 假设所有语句都在同一行仅在同一行，所有语句都仅判断第一个错误
    private static void subProgram() {
        boolean flag;
        boolean flagSemicolon;
        // 将tuples按语句分
        for (int i = 0; i < tuples.size(); i++) {
            switch (tuples.get(i).getContent().toLowerCase()) {
                case "const":
                    i++;
                    flag = true;
                    flagSemicolon = false;
                    while (flag && i < tuples.size()) {
                        // 判断 identifier = constant
                        if (tuples.get(i).getNumber() == 2) { // identifier
                            i++;
                            if (tuples.get(i).getNumber() == 8) { // =
                                i++;
                                if (tuples.get(i).getNumber() == 3) { // constant
                                    i++;
                                } else {
                                    errors.add(new Error(lines, "'='后面应是'constant'"));
                                }
                            } else {
                                errors.add(new Error(lines, "'identifier'后面应是'='"));
                            }
                        } else {
                            errors.add(new Error(lines, "'const'或','后面应是'identifier'"));
                        }

                        // 判断 , ;
                        //noinspection Duplicates
                        if (tuples.get(i).getNumber() == 0) { // 换行
                            break;
                        } else if (tuples.get(i).getNumber() == 18) { // ;
                            flag = false;
                            flagSemicolon = true;
                        } else if (tuples.get(i).getNumber() == 17) { // ,
                            i++;
                        } else {
                            errors.add(new Error(lines, "缺少','或';'"));
                            i++;
                        }
                    }
                    if (!flagSemicolon) {
                        errors.add(new Error(lines, "缺少;"));
                    }
                    while (tuples.get(i).getNumber() != 0) {
                        i++;
                    }
                    lines++;
                    break;
                case "var":
                    i++;
                    flag = true;
                    flagSemicolon = false;
                    while (flag && i < tuples.size()) {
                        // 判断 identifier
                        if (tuples.get(i).getNumber() == 2) {
                            i++;
                        } else {
                            errors.add(new Error(lines, "'var'或','后面应是'identifier'"));
                        }
                        // 判断 , ;
                        //noinspection Duplicates
                        if (tuples.get(i).getNumber() == 0) { // 换行
                            break;
                        } else if (tuples.get(i).getNumber() == 18) { // ;
                            flag = false;
                            flagSemicolon = true;
                        } else if (tuples.get(i).getNumber() == 17) { // ,
                            i++;
                        } else {
                            errors.add(new Error(lines, "缺少','或';'"));
                            i++;
                        }
                    }
                    if (!flagSemicolon) {
                        errors.add(new Error(lines, "缺少;"));
                    }
                    while (tuples.get(i).getNumber() != 0) {
                        i++;
                    }
                    lines++;
                    break;
                case "procedure":
                    // 先考虑分程序只有一个的情况
                    i++;
                    // flag = true;
                    // while (flag && i < tuples.size()) {
                    // 判断 identifier
                    if (tuples.get(i).getNumber() == 2) { // identifier
                        i++;
                        if (tuples.get(i).getNumber() == 18) { // ;
                            i++;
                            // flag = false;
                        } else {
                            errors.add(new Error(lines, "缺少;"));
                        }
                    } else {
                        errors.add(new Error(lines, "'procedure'后面应是'identifier'"));
                    }
                    while (tuples.get(i).getNumber() != 0) {
                        i++;
                    }
                    // }
                    lines++;
                    break;
                default:
                    // 进入sentence
                    i = sentence(i);
                    if (tuples.get(i).getContent().equals("\n")) {
                        lines++;
                    }
                    break;
            }
        }
        if (beginEndFlag > 0) {
            errors.add(new Error(lines, "程序缺少end"));
        }
    }

    // 语句，以一个语句为单位来判断分号
    private static int sentence(int i) {
        boolean semicolon = false;
        int j;
        switch (tuples.get(i).getNumber()) {
            case 1: // keyword
                switch (tuples.get(i).getContent().toLowerCase()) {
                    case "call":
                        i++;
                        if (tuples.get(i).getNumber() == 2) { // identifier
                            i++;
                        } else {
                            errors.add(new Error(lines, "'call'后面应是'identifier'"));
                        }
                        if (tuples.get(i).getNumber() == 18) { // ;
                            semicolon = true;
                        }
                        break;
                    case "begin":
                        beginEndFlag++;
                        semicolon = true;
                        break;
                    case "end":
                        beginEndFlag--;
                        if (beginEndFlag < 0) {
                            errors.add(new Error(lines, "该End缺少对应的begin"));
                        }
                        semicolon = true;
                        break;
                    // 假设if和then在同一行
                    case "if":
                        i++;
                        j = condition(i);
                        if (j == i) {
                            errors.add(new Error(lines, "if后面缺少condition"));
                        } else {
                            i = j;
                            if (tuples.get(i).getContent().equals("then")) {
                                i++;
                            } else {
                                errors.add(new Error(lines, "if缺少对应的then"));
                            }
                        }
                        semicolon = true;
                        break;
                    // 假设while和do在同一行
                    case "while":
                        i++;
                        j = condition(i);
                        if (j == i) {
                            errors.add(new Error(lines, "while后面缺少condition"));
                        } else {
                            i = j;
                            if (tuples.get(i).getContent().equals("do")) {
                                i++;
                            } else {
                                errors.add(new Error(lines, "while缺少对应的do"));
                            }
                        }
                        semicolon = true;
                        break;
                    case "read":
                        i++;
                        if (tuples.get(i).getContent().equals("(")) {
                            // 判断identifier
                            i++;
                            boolean loop = true;
                            while (loop) {
                                if (tuples.get(i).getNumber() == 2) { // identifier
                                    i++;
                                    if (tuples.get(i).getNumber() == 17) { // ,
                                        i++;
                                    } else {
                                        loop = false;
                                    }
                                } else {
                                    errors.add(new Error(lines, "read()中'('或','后面应是identifier"));
                                    break;
                                }
                            }
                            // 判断)
                            if (tuples.get(i).getContent().equals(")")) {
                                i++;
                            } else {
                                errors.add(new Error(lines, "read中缺少)"));
                            }
                        } else {
                            errors.add(new Error(lines, "read中缺少("));
                        }
                        while (tuples.get(i).getNumber() != 0) {
                            if (tuples.get(i).getNumber() == 18) { // ;
                                semicolon = true;
                            }
                            i++;
                        }
                        break;
                    case "write":
                        i++;
                        if (tuples.get(i).getContent().equals("(")) {
                            // 判断expression
                            i++;
                            boolean loop = true;
                            while (loop) {
                                j = expression(i);
                                if (j == i) {
                                    errors.add(new Error(lines, "write中缺少表达式"));
                                    break;
                                } else {
                                    i = j;
                                }
                                if (tuples.get(i).getNumber() == 17) { // ,
                                    i++;
                                } else {
                                    loop = false;
                                }
                            }
                            // 判断)
                            if (tuples.get(i).getContent().equals(")")) {
                                i++;
                            } else {
                                errors.add(new Error(lines, "write中缺少)"));
                            }
                        } else {
                            errors.add(new Error(lines, "write中缺少("));
                        }
                        while (tuples.get(i).getNumber() != 0) {
                            if (tuples.get(i).getNumber() == 18) { // ;
                                semicolon = true;
                            }
                            i++;
                        }
                        break;
                    default:
                        errors.add(new Error(lines, "未知的关键字"));
                        System.out.println("未知的关键字: " + tuples.get(i).getContent());
                        break;
                }
                break;
            case 2: // identifier
                i++;
                if (tuples.get(i).getNumber() == 19) { // :=
                    i++;
                    j = expression(i);
                    if (i == j) {
                        errors.add(new Error(lines, "表达式中缺少项"));
                    } else {
                        i = j;
                        if (tuples.get(i).getContent().equals(";")) {
                            semicolon = true;
                        }
                    }
                } else {
                    errors.add(new Error(lines, "'identifier'后面应是':='"));
                }
                break;
            default:
                errors.add(new Error(lines, "未知的字符"));
                System.out.println("未知的字符: " + tuples.get(i).getContent());
                break;
        }
        if (!semicolon) {
            errors.add(new Error(lines, "缺少;"));
        }
        while (i < tuples.size() - 1 && tuples.get(i).getNumber() != 0) {
            i++;
        }
        return i;
    }

    // 条件
    private static int condition(int i) {
        int j = expression(i);
        if (j == i) {
            errors.add(new Error(lines, "条件中operator前缺少表达式"));
        } else {
            i = j;
            String operator = tuples.get(i).getContent();
            if (operator.equals("=") || operator.equals(">") || operator.equals("<") ||
                    operator.equals(">=") || operator.equals("<=") || operator.equals("#")) {
                i++;
                j = expression(i);
                if (j == i) {
                    errors.add(new Error(lines, "条件中operator后缺少表达式"));
                } else {
                    i = j;
                }
            } else {
                errors.add(new Error(lines, "条件中缺少operator"));
            }
        }
        return i;
    }

    // 表达式
    private static int expression(int i) {
        int j;
        boolean loop = true;
        while (loop) {
            j = item(i);
            if (i == j) {
                errors.add(new Error(lines, "项中缺少因子"));
                loop = false;
            } else {
                i = j;
                String temp = tuples.get(i).getContent();
                if (temp.equals("+") || temp.equals("-")) {
                    i++;
                } else {
                    loop = false;
                }
            }
        }
        return i;
    }

    // 项
    private static int item(int i) {
        int j;
        boolean loop = true;
        while (loop) {
            j = element(i);
            if (j == i) {
                errors.add(new Error(lines, "因子缺少'identifier'或'constant'或'(表达式)'"));
            } else {
                i = j;
            }
            String temp = tuples.get(i).getContent();
            if (temp.equals("*") || temp.equals("/")) {
                i++;
            } else {
                loop = false;
            }
        }
        return i;
    }

    // 因子 （仅考虑constant和identifier）
    private static int element(int i) {
        if (tuples.get(i).getNumber() == 2 || tuples.get(i).getType().equals("constant")) {
            i++;
        }
        return i;
    }
}
