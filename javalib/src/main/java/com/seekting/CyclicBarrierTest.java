package com.seekting;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Administrator on 2017/9/21.
 */

public class CyclicBarrierTest {

    public static void main(String args[]) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
        for (int i = 0; i < cyclicBarrier.getParties(); i++) {
            final int j = i;
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(j * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("await");
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println("pass");
                }
            }.start();
        }
    }

}
