package com.mashibing.springboot.other.threadLock;

import java.util.concurrent.CyclicBarrier;

public class T012_ThreadCyclicBarrier {
    int count = 0;
    CyclicBarrier barrier = new CyclicBarrier(5, () -> {
        ++this.count;
        System.out.println("满员");
    });

    public T012_ThreadCyclicBarrier() {
    }

    public static void main(String[] args) throws Exception {
        T012_ThreadCyclicBarrier cb = new T012_ThreadCyclicBarrier();

        for(int i = 0; i < 20; ++i) {
            (new Thread(() -> {
                try {
                    cb.barrier.await();
                } catch (Exception var2) {
                    var2.printStackTrace();
                }

            })).start();
        }

    }
}
