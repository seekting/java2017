package com.seekting;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by Administrator on 2017/11/1.
 */

public class Mutex extends AbstractQueuedSynchronizer {
    @Override
    protected boolean tryAcquire(int arg) {
        if (compareAndSetState(0, 1)) {
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;

    }

    @Override
    protected boolean tryRelease(int arg) {
        if (getState() == 1) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }
        return false;
    }

    public void lock() {
        acquire(1);
    }

    public void unlock() {
        release(1);

    }

    public static void main(String args[]) {
        final Mutex mutex = new Mutex();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mutex.lock();
                mutex.lock();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mutex.unlock();
                mutex.unlock();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("begin!");
                mutex.lock();
                System.out.println("gogogo!");
                mutex.unlock();
            }
        }).start();

    }
}
