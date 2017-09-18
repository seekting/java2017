package com;

/**
 * Created by Administrator on 2017/8/30.
 */

public class Testji {
    public static void main(String args[]) {

        int j = 0;
        for (int x = 0; x < 34; x++) {
            for (int y = 0; y < 21; y++) {
                int z = 100 - 3 * x - 5 * y;
                if (x + y + 3 * z == 100) {
//                            System.out.println("result x=" + x + ",y=" + y + ",z=" + z);
                    System.out.println("result x=" + x + ",y=" + y + ",z=" + 3 * z);

                }
//
                    j++;
            }

        }
        System.out.println("j=" + j);
    }
}
