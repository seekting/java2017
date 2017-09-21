package com.seekting;

/**
 * Created by Administrator on 2017/9/21.
 */

public class MaxAdd {

    public static void main(String args[]) {

        int array[] = new int[]{3, 2, -4, -5, 5, -3, 6};
        int find = max(array);
        System.out.println("find=" + find);
    }



    public static int max(int[] array) {

        int max = 0;
        int thismax = 0;
        for (int i = 0; i < array.length; i++) {

            thismax = array[i] + thismax;
            if (thismax > max) {
                max = thismax;
            }
            if (thismax < 0) {
                thismax = 0;

            }
        }
        return max;

    }
}
