package com.mashibing.springboot.other.queue;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class T001_ConcurrentMap {
    public T001_ConcurrentMap() {
    }

    public static void main(String[] args) throws Exception {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap();
        Random random = new Random();
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);
        long startTime = System.currentTimeMillis();

        for(int i = 0; i < threads.length; ++i) {
            threads[i] = new Thread(() -> {
                for(int j = 0; j < 10000; ++j) {
                    map.put("a" + random.nextInt(1000000000), "a" + random.nextInt(100000));
                }

                latch.countDown();
            });
        }

        Arrays.asList(threads).forEach((t) -> {
            t.start();
        });
        latch.await();
        System.out.println(map.size());
        System.out.println(System.currentTimeMillis() - startTime);
    }
}

