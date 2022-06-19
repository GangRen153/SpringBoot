package com.mashibing.springboot.other.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class T005_ThreadFutureTask {
    public T005_ThreadFutureTask() {
    }

    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newCachedThreadPool();
        FutureTask<Integer> futureTask = new FutureTask(() -> {
            TimeUnit.MILLISECONDS.sleep(1000L);
            return 1;
        });
        Integer value = (Integer)futureTask.get();
        System.out.println(value);
        service.shutdown();
    }
}

