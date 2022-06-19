package com.mashibing.springboot.other.queue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class T004_ThreadBlockingQueue {
    public T004_ThreadBlockingQueue() {
    }

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue(100);
        Random r = new Random();
        new Thread(() -> {
            for(int i = 0; i < 200; ++i) {
                try {
                    queue.put(r.nextInt(10000));
                    TimeUnit.MILLISECONDS.sleep(100L);
                } catch (InterruptedException var4) {
                    var4.printStackTrace();
                }
            }

        });
        new Thread(() -> {
            while(true) {
                try {
                    Integer var1 = (Integer)queue.take();
                } catch (InterruptedException var2) {
                    var2.printStackTrace();
                }
            }
        });
    }
}
