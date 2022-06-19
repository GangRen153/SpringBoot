package com.mashibing.springboot.other.threadLock;

import java.util.concurrent.Semaphore;

public class T015_ThreadSemaphore {
    volatile int count = 0;
    Semaphore semaphore = new Semaphore(1);

    public T015_ThreadSemaphore() {
    }

    public static void main(String[] args) {
        T015_ThreadSemaphore threadSemaphore = new T015_ThreadSemaphore();
        (new Thread(() -> {
            try {
                threadSemaphore.semaphore.acquire();
                Thread.sleep(500L);
                ++threadSemaphore.count;
                System.out.println("t1... -> " + threadSemaphore.count);
            } catch (InterruptedException var5) {
                var5.printStackTrace();
            } finally {
                threadSemaphore.semaphore.release();
            }

        })).start();
        (new Thread(() -> {
            try {
                threadSemaphore.semaphore.acquire();
                Thread.sleep(500L);
                ++threadSemaphore.count;
                System.out.println("t2... -> " + threadSemaphore.count);
            } catch (InterruptedException var5) {
                var5.printStackTrace();
            } finally {
                threadSemaphore.semaphore.release();
            }

        })).start();
    }
}
