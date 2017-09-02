package com;

import java.util.Stack;

/**
 * Created by Administrator on 2017/9/2.
 */

public class StackSort {
    public static void main(String args[]) {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(5);
        stack.push(3);
        stack.push(4);
        stack.push(1);
        System.out.println(stack);
        sort(stack);
        System.out.println(stack);


    }

    private static void sort(Stack<Integer> src) {
        if (src == null || src.size() < 2) {
            return;
        }

        Stack<Integer> temp = new Stack<>();
        for (int i = 0; i < src.size(); i++) {
            int maxIndex = 0;
            int max = 0;
            boolean first = true;
            while (src.size() != i) {
                int pop = src.pop();
                if (first) {
                    max = pop;
                    maxIndex = 0;
                    first = false;
                } else {
                    if (pop > max) {
                        max = pop;
                        maxIndex = 0;
                    } else {
                        maxIndex++;
                    }
                }
                temp.push(pop);

            }
            src.push(max);
            while (!temp.isEmpty()) {
                int tempPop = temp.pop();
                if (maxIndex == 0) {
                    maxIndex--;
                } else {
                    maxIndex--;
                    src.push(tempPop);
                }


            }


        }


    }
}
