package com.mashibing.springboot.other.threadLock;

import java.util.concurrent.TimeUnit;

public class T005_ThreadSynchronized1 {
    Object o = new Object();
    Object o1 = new Object();

    public T005_ThreadSynchronized1() {
    }

    public void m1() {
        for(int i = 0; i < 10; ++i) {
            synchronized(this.o) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500L);
                } catch (Exception var5) {
                }

                System.out.println("m1:" + i);
            }
        }

    }

    public synchronized void m2() {
        System.out.println("m2");
    }

    public static void main(String[] args) throws Exception {
        T005_ThreadSynchronized1 rl = new T005_ThreadSynchronized1();
        rl.getClass();
        Thread t1 = new Thread(rl::m1);
        rl.getClass();
        Thread t2 = new Thread(rl::m2);
        t1.start();
        t2.start();
        Thread.sleep(5000L);
    }
}

