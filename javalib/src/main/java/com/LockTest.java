package com;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/9/20.
 */

public class LockTest {
    static Lock sLock = new ReentrantLock(false);


    public static void print(int i, boolean sleep) {

        System.out.println("" + i);
//        sLock.lock();
        synchronized (LockTest.class) {
            if (sleep) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("" + i);
//            sLock.unlock();
        }

    }

    public static void main(String args[]) {


        for (int i = 0; i < 5000; i++) {
            final int j = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    print(j, j == 0);
                }
            }).start();
            if (j == 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
