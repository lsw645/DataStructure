package com.lxw.data.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <pre>
 *     author : lxw
 *     e-mail : lsw@tairunmh.com
 *     time   : 2018/05/31
 *     desc   :
 * </pre>
 */

class Main {
    public static void main(String[] args) {
        //9 3 1-3*+ 10 2/+”
        String ss = "9+(3-1)*3+2/2";
        String data = convertToReversePolishNotation(ss);
    }

    /**
     * 中缀表达式转后缀表达式
     *
     * @param str 字符串
     * @return 后缀表达式
     */
    private static String convertToReversePolishNotation(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException();
        }
        str = str.replace(" ", "");
        Stack<Character> numberStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();
        List<Character> array = new ArrayList<>(str.length());
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')') {
                if (c == ')') {
                    while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                        Character ch = operatorStack.pop();
                        if (ch != '(') {
                            array.add(ch);
                        }

                    }
                    operatorStack.pop();
                } else if (c == '+' || c == '-') {
                    if (!operatorStack.isEmpty() && (operatorStack.peek() == '*' || operatorStack.peek() == '/')) {
                        while (!operatorStack.isEmpty()) {
                            array.add(operatorStack.pop());
                        }
                        operatorStack.push(c);
                    } else {
                        operatorStack.push(c);
                    }
                } else {
                    operatorStack.push(c);
                }

            } else {
                array.add(c);
            }
        }
        while (!operatorStack.isEmpty()) {
            array.add(operatorStack.pop());
        }

        StringBuilder sb = new StringBuilder(array.size());
        for (Character character : array) {
            sb.append(character);
        }
        return sb.toString();
    }

}
