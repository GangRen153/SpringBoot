package com.mashibing.springboot.other.queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class T002_ThreadArrayList {
    public T002_ThreadArrayList() {
    }

    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList();
        Collections.synchronizedList(new ArrayList());
        Random r = new Random();
        long startTime = System.currentTimeMillis();
        Thread[] threads = new Thread[100];

        for(int i = 0; i < threads.length; ++i) {
            threads[i] = new Thread(() -> {
                for(int j = 0; j < 1000; ++j) {
                    list.add("a" + r.nextInt(10000));
                }

            });
        }

        Arrays.asList(threads).forEach((t) -> {
            t.start();
        });
        Arrays.asList(threads).forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException var2) {
                var2.printStackTrace();
            }

        });
        System.out.println(list.size());
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
