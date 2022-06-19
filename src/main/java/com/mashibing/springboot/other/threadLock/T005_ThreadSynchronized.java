package com.mashibing.springboot.other.threadLock;

public class T005_ThreadSynchronized {
    int count = 0;

    public T005_ThreadSynchronized() {
    }

    public synchronized void m1() {
        ++this.count;
    }

    public void m2() {
        ++this.count;
    }

    public static void main(String[] args) throws Exception {
        T005_ThreadSynchronized t = new T005_ThreadSynchronized();
        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 1000; ++i) {
                t.m1();
            }

        }, "t2");
        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 1000; ++i) {
                t.m2();
            }

        }, "t1");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(t.count);
    }
}
