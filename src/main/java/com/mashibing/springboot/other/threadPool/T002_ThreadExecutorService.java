package com.mashibing.springboot.other.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T002_ThreadExecutorService {
    public T002_ThreadExecutorService() {
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(() -> {
            System.out.println("提交一个新的线程任务开始运行");
        });
    }
}
