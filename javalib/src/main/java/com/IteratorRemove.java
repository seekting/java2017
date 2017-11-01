package com;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017/9/21.
 */

public class IteratorRemove {

    public static void main(String args[]) {

        List<String> integers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            integers.add("" + i);
        }

        Iterator<String> iterator = integers.iterator();
        integers.add("111");
        iterator.next();
//        while (iterator.hasNext()) {
//            String j = iterator.next();
//            System.out.println("j=" + j);
//            int res = Integer.parseInt(j);
//            if (res % 3 == 0 || res % 2 == 0) {
//                iterator.remove();
//            }
//
//        }


//        for (int i = 0; i < integers.size(); i++) {
//            String j = integers.get(i);
//
//            System.out.println("j=" + j);
////            if (Integer.parseInt(j) == 1 || Integer.parseInt(j) == 2) {
////                integers.remove(i);
////            }
//            if (Integer.parseInt(j) % 3 == 0 || Integer.parseInt(j) % 2 == 0) {
//                integers.remove(j);
//            }
//        }

//        int i = 0;
//        while (i < integers.size()) {
//            String j = integers.get(i);
//            System.out.println("j" + j);
//            if (Integer.parseInt(j) == 1 || Integer.parseInt(j) == 2) {
//                integers.remove(i);
//            } else {
//                i++;
//
//            }
//
//        }
//        for (String j : integers) {
//            if (Integer.parseInt(j) % 2 == 0) {
//                integers.remove(j);
//            }
//
        System.out.println("" + integers);
//
//        }
    }
}
