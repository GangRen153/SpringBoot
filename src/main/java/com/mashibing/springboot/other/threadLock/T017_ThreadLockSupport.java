package com.mashibing.springboot.other.threadLock;

import java.util.concurrent.locks.LockSupport;

public class T017_ThreadLockSupport {
    public T017_ThreadLockSupport() {
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 10; ++i) {
                try {
                    if (i == 5) {
                        LockSupport.park();
                    }

                    Thread.sleep(200L);
                } catch (InterruptedException var2) {
                    var2.printStackTrace();
                }

                System.out.println(i);
            }

        });
        t1.start();
        Thread.sleep(3000L);
        LockSupport.unpark(t1);
    }
}
