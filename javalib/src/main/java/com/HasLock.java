package com;

/**
 * Created by Administrator on 2017/9/16.
 */

public class HasLock {

    public static void main(String args[]) {
        final Object lock = new Object();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    synchronized (lock) {
                        lock.wait();
                    System.out.println(Thread.holdsLock(lock));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        synchronized (lock) {
            lock.notify();
        }
    }


}
