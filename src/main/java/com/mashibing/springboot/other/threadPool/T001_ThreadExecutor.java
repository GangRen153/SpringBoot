package com.mashibing.springboot.other.threadPool;

import java.util.concurrent.Executor;

public class T001_ThreadExecutor implements Executor {
    public T001_ThreadExecutor() {
    }

    public void execute(Runnable command) {
        command.run();
    }

    public static void main(String[] args) {
        (new T001_ThreadExecutor()).execute(() -> {
            System.out.println("线程池Executor执行");
        });
    }
}