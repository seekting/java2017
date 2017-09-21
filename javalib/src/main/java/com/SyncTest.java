package com;

/**
 * Created by Administrator on 2017/9/20.
 */

public class SyncTest {

    public static void print(int i, boolean sleep) {
        System.out.println("begin" + i);
        synchronized (SyncTest.class) {
            if (sleep) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("end" + i);
    }

    public static void main(String args[]) {


        for (int i = 0; i < 5; i++) {
            final int j = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    print(j, j == 0);
                }
            }).start();
            if(j==0){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
