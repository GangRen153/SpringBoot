package com.mashibing.springboot.other.threadLock;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class T001_CreateThread {
    public T001_CreateThread() {
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        (new Thread(() -> {
            System.out.println("1");
        })).start();
        (new MyThread()).start();
        (new Thread(new MyRunnable())).start();
        FutureTask<String> task = new FutureTask(new MyCallable());
        Thread thread = new Thread(task);
        thread.start();
        String s = (String)task.get();
        ExecutorService callableService = Executors.newCachedThreadPool();
        Future<String> future = callableService.submit(new MyCallable());
        String value = (String)future.get();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            System.out.println();
        });
        service.shutdown();
    }

    static class MyCallable implements Callable<String> {
        MyCallable() {
        }

        public String call() throws Exception {
            return "测试 Callable 线程返回参数";
        }
    }

    static class MyRunnable implements Runnable {
        MyRunnable() {
        }

        public void run() {
            System.out.println();
        }
    }

    static class MyThread extends Thread {
        MyThread() {
        }

        public void run() {
            super.run();
        }
    }
}

