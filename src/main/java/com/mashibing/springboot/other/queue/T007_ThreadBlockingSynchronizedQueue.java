package com.mashibing.springboot.other.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class T007_ThreadBlockingSynchronizedQueue {
    public T007_ThreadBlockingSynchronizedQueue() {
    }

    public static void main(String[] args) throws Exception {
        BlockingQueue<Integer> queue = new SynchronousQueue();
        (new Thread(() -> {
            try {
                while(true) {
                    TimeUnit.MILLISECONDS.sleep(1000L);
                    Integer take = (Integer)queue.take();
                    System.out.println("t1:" + take);
                }
            } catch (InterruptedException var2) {
                var2.printStackTrace();
            }
        })).start();
        queue.put(1);
        System.out.println("---1");
        queue.put(2);
        System.out.println("---2");
        queue.put(3);
        System.out.println("---3");
    }
}
