package com.mashibing.springboot.other.queue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class T003_ThreadConcurrentLinkedQueue {
    public T003_ThreadConcurrentLinkedQueue() {
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new ConcurrentLinkedQueue();

        for(int i = 0; i < 10; ++i) {
            queue.add(i);
        }

        Integer poll = (Integer)queue.poll();
        System.out.println(queue.size());
        queue.peek();
        System.out.println(queue.size());
    }
}

