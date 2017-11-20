package com;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/9/27.
 */

public class Person {

    private int age;
    public final String name = "";
    public static String mStatic;
    protected boolean isMan;

    public static class Man extends Person {

        private int aage;
    }

    public static void main(String args[]) {
//        Field[] fields = Person.class.getFields();
//        for (Field f : fields) {
//            System.out.println("" + f.toString());
//        }
//        System.out.println("---------------");
//        fields = Person.class.getDeclaredFields();
//        for (Field f : fields) {
//            System.out.println("" + f.toString());
//        }

//
//        Field[] fields = Man.class.getFields();
//        for (Field f : fields) {
//            System.out.println("" + f.toString());
//        }
//        System.out.println("---------------");
//        fields = Man.class.getDeclaredFields();
//        for (Field f : fields) {
//            System.out.println("" + f.toString());
//        }

        final Lock lock = new ReentrantLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                lock.lock();
                lock.lock();
//                lock.unlock();

            }
        }).start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                lock.lock();
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                lock.lock();
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                lock.lock();
//            }
//        }).start();
//
    }
}
