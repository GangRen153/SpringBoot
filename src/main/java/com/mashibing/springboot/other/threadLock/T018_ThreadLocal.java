package com.mashibing.springboot.other.threadLock;

import java.util.concurrent.TimeUnit;

public class T018_ThreadLocal {
    public T018_ThreadLocal() {
    }

    public static void main(String[] args) throws Exception {
        ThreadLocal tl = new ThreadLocal();
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000L);
            } catch (InterruptedException var2) {
                var2.printStackTrace();
            }

            tl.set("我是t1线程");
            System.out.println("t1:" + tl.get());
        });
        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500L);
            } catch (InterruptedException var2) {
                var2.printStackTrace();
            }

            System.out.println("t2:" + tl.get());
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
