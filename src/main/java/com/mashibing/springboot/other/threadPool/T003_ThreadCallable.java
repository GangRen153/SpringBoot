package com.mashibing.springboot.other.threadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class T003_ThreadCallable {
    public T003_ThreadCallable() {
    }

    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newCachedThreadPool();
        Callable<String> callable = () -> {
            TimeUnit.MILLISECONDS.sleep(1000L);
            return "线程返回参数";
        };
        Future<String> submit = service.submit(callable);
        String value = (String)submit.get();
        System.out.println(value);
        service.shutdown();
    }
}
