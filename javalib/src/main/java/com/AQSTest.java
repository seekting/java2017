package com;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by Administrator on 2017/9/21.
 */

public class AQSTest {

    private static class MyAQS extends AbstractQueuedSynchronizer {
        protected boolean tryAcquire(int arg) {
            int state = getState();
            if (state == 0) {
                setState(arg);
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            } else if (state > 0 && Thread.currentThread() == getExclusiveOwnerThread()) {
                setState(state + arg);
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            Thread current = Thread.currentThread();
            if (current == getExclusiveOwnerThread()) {

                int result = getState() - arg;
                setState(result);
                if (result == 0) {
                    setExclusiveOwnerThread(null);
                    return true;
                } else {
                    return false;
                }
            } else {
                throw new RuntimeException("haha");
            }
        }
    }

    public static void main(String args[]) {
        final MyAQS myAQS = new MyAQS();
        myAQS.acquire(1);
        System.out.println("I have lock");

        new Thread() {
            @Override
            public void run() {
                System.out.println("Thread try acquire");
                myAQS.acquire(1);
                System.out.println("Thread try acquires suc!");
            }
        }.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myAQS.release(1);
        System.out.println("release!");
    }
}
