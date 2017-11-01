package com;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2017/9/21.
 */

public class CutDownTest {
    public static void main(String args[]) {
        final CountDownLatch countDownLatch = new CountDownLatch(4);

        for (int i = 0; i < countDownLatch.getCount(); i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("pass");
                    countDownLatch.countDown();
                }
            }.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("gogogo!");

    }
}
