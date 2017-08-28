package com;

import java.util.ArrayList;
import java.util.List;

/**
 * 2932159[ 1-2+3 4 5+6+7+8+9]
 * Created by Administrator on 2017/8/28.
 */

public class calc {

    public static void main(String args[]) {
        char[] array = new char[]{'0', '1', '0', '2', '0', '3', '0', '4', '0', '5', '0', '6', '0', '7', '0', '8', '0', '9'};
//        char[] array = new char[]{'+', '1', '+', '2', '+', '3', '+', '4', '+', '5', '+', '6', '+', '7', '+', '8', '+', '9'};
        char[] operate = new char[]{'+', '-', ' '};
//
        setArray(array, 0, operate);
//        calc(array);
    }

    private static void setArray(char[] array, int index, char[] operate) {
        if (index == array.length) {
            calc(array);
            return;

        }
        for (int j = 0; j < operate.length; j++) {
            array[index] = operate[j];
//            print(array);
            setArray(array, index + 2, operate);
        }
    }

    //
    private static void print(char[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                System.out.print("[");
            }
            System.out.print(array[i]);
        }
        System.out.println("]");
    }

    static char lastOperat = 0;
    static char result = '0';
    static boolean resultIsFu = false;

    public static void calc(char[] array) {
        List<Character> list = new ArrayList<>();
        lastOperat = 0;
        result = '0';
        resultIsFu = false;
        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                list.add(result);
            }
            char c = array[i];
            switch (c) {
            case ' ':
                break;
            case '+':
            case '-':
                doOperate(c, i, array, list, result);
                break;
            default:
                list.add(c);
                if (i == array.length - 1) {
                    doOperate('+', i, array, list, result);
                }
                break;
            }


        }


    }

    private static void doOperate(char cchar, int i, char[] array, List<Character> list, char result) {
        if (lastOperat != 0) {
            int left = 0;
            int right = 0;
            boolean isLeft = true;
            for (int index = 0; index < list.size(); index++) {
                char cc = list.get(index);
                if (cc == lastOperat) {
                    isLeft = false;
                } else {
                    if (isLeft) {
                        left = left * 10 + (cc - '0');
                    } else {
                        right = right * 10 + (cc - '0');

                    }
                }
            }
            int intResult = 0;
            if (lastOperat == '-') {
                intResult = left - right;

            } else if (lastOperat == '+') {
                intResult = left + right;
            }
            int realResult = result - '0';
            if (resultIsFu) {
                realResult = -realResult;
            }
            realResult = realResult + intResult;
            resultIsFu = realResult < 0;
            result = (char) (Math.abs(realResult) + '0');
            if (i == array.length - 1) {
                System.out.print(realResult);
//                if (realResult == 100) {
                print(array);
//                }
                return;
            }
            list.clear();
            list.add(result);
            list.add(cchar);

        } else {

            lastOperat = cchar;
            list.add(cchar);
        }
    }
}
