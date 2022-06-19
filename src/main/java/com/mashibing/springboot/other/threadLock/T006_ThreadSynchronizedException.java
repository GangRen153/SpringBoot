package com.mashibing.springboot.other.threadLock;

public class T006_ThreadSynchronizedException {
    int count = 0;

    public T006_ThreadSynchronizedException() {
    }

    synchronized void m1() {
        for(; this.count < 10; System.out.println(this.count)) {
            ++this.count;

            try {
                Thread.sleep(10L);
            } catch (Exception var2) {
            }

            if (this.count == 5) {
                int var1 = 1 / 0;
            }
        }

    }

    public static void main(String[] args) {
        T006_ThreadSynchronizedException t = new T006_ThreadSynchronizedException();
        Thread t1 = new Thread(() -> {
            t.m1();
        });
        Thread t2 = new Thread(() -> {
            t.m1();
        });
        t1.start();
        t2.start();
    }
}

