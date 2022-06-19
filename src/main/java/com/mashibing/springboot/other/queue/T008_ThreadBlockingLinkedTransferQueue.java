package com.mashibing.springboot.other.queue;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

public class T008_ThreadBlockingLinkedTransferQueue {
    public T008_ThreadBlockingLinkedTransferQueue() {
    }

    public static void main(String[] args) throws Exception {
        LinkedTransferQueue<Integer> queue = new LinkedTransferQueue();
        (new Thread(() -> {
            try {
                while(true) {
                    TimeUnit.MILLISECONDS.sleep(3000L);
                    Integer take = (Integer)queue.take();
                    System.out.println("t1:" + take);
                }
            } catch (InterruptedException var2) {
                var2.printStackTrace();
            }
        })).start();
        queue.transfer(1);
        System.out.println("main1");
        queue.transfer(2);
        System.out.println("main2");
        System.in.read();
    }
}
