package com;

import java.util.Stack;

/**
 * Created by Administrator on 2017/9/2.
 */

public class StackReverse {

    public static void main(String args[]) {

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i + 1);
        }
        System.out.println(stack);
        reverse(stack);
        System.out.println(stack);
    }

    /**
     * 1  [5,4,3,2]
     * 2 [5,4,3]
     * 3[5,4]
     * 4[5]
     * 5
     *
     * @param stack
     */
    private static void reverse(Stack<Integer> stack) {

        if (stack.isEmpty()) {
            return;
        }
        int pop = getAndPop(stack);
        reverse(stack);
        stack.push(pop);


    }

    /**
     * [2,1]->[2]
     * [3,2,1]
     * @param stack
     * @return
     */
    private static int getAndPop(Stack<Integer> stack) {

        int pop = stack.pop();
        if (stack.isEmpty()) {
            return pop;

        } else {
            int result = getAndPop(stack);
            stack.push(pop);
            return result;
        }
    }
}
