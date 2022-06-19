package com.mashibing.springboot.other.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T008_ThreadWorkStealingPool {
    public T008_ThreadWorkStealingPool() {
    }

    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newWorkStealingPool();
        service.execute(new WorkTask(1000L));
        service.execute(new WorkTask(2000L));
        service.execute(new WorkTask(4000L));
        service.execute(new WorkTask(2000L));
        System.in.read();
    }

    static class WorkTask implements Runnable {
        long time;

        public WorkTask(long time) {
            this.time = time;
        }

        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(this.time);
            } catch (InterruptedException var2) {
                var2.printStackTrace();
            }

            System.out.println(this.time + " " + Thread.currentThread().getName());
        }
    }
}

