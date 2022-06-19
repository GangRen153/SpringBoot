package com.mashibing.springboot.other.threadLock;

import java.util.concurrent.CountDownLatch;

public class T011_ThreadCountDownLatch {
    int count = 0;
    Thread[] threads = new Thread[1000];
    CountDownLatch latch;

    public T011_ThreadCountDownLatch() {
        this.latch = new CountDownLatch(this.threads.length);
    }

    void m1() {
        for(int i = 0; i < this.threads.length; ++i) {
            this.threads[i] = new Thread(() -> {
                ++this.count;
                this.latch.countDown();
            });
        }

        Thread[] var6 = this.threads;
        int var2 = var6.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Thread thread = var6[var3];
            thread.start();
        }

        try {
            this.latch.await();
        } catch (Exception var5) {
        }

        System.out.println("end:" + this.count);
    }

    public static void main(String[] args) {
        T011_ThreadCountDownLatch latch = new T011_ThreadCountDownLatch();
        latch.m1();
    }
}

