package com.mashibing.springboot.other.threadLock;

import java.util.concurrent.locks.LockSupport;

public class T002_ThreadState {
    public T002_ThreadState() {
    }

    public static void main(String[] args) throws Exception {
        Thread t = new Thread();
        t.start();
        Thread.yield();
        Thread.sleep(1000L);
        LockSupport.park();
    }
}
