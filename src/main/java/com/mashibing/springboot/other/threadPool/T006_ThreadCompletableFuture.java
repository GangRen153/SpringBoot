package com.mashibing.springboot.other.threadPool;

import java.util.concurrent.CompletableFuture;

public class T006_ThreadCompletableFuture {
    public T006_ThreadCompletableFuture() {
    }

    public static void main(String[] args) {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future1");
            return 1;
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future1");
            return 2;
        });
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future1");
            return 3;
        });
        future1.thenApply((value) -> {
            return "测试1" + value;
        }).thenAccept((s) -> {
            System.out.println(s);
        });
        future2.thenApply((value) -> {
            return "测试2" + value;
        }).thenAccept((s) -> {
            System.out.println(s);
        });
        future3.thenApply((value) -> {
            return "测试3" + value;
        }).thenAccept((s) -> {
            System.out.println(s);
        });
        CompletableFuture.allOf(future1, future2, future3).join();
    }
}
