package com.mashibing.springboot.other.threadLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class T014_ThreadReadWriteLock {
    Lock lock = new ReentrantLock();
    ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    Lock readLock;
    Lock writeLock;

    public T014_ThreadReadWriteLock() {
        this.readLock = this.readWriteLock.readLock();
        this.writeLock = this.readWriteLock.writeLock();
    }

    void read(Lock lock) {
        lock.lock();

        try {
            Thread.sleep(200L);
        } catch (InterruptedException var6) {
            var6.printStackTrace();
        } finally {
            lock.unlock();
        }

        System.out.println("read over");
    }

    void write(Lock lock) {
        lock.lock();

        try {
            Thread.sleep(200L);
        } catch (InterruptedException var6) {
            var6.printStackTrace();
        } finally {
            lock.unlock();
        }

        System.out.println("write over");
    }

    public static void main(String[] args) {
        T014_ThreadReadWriteLock rwl = new T014_ThreadReadWriteLock();

        for(int i = 0; i < 10; ++i) {
            if (i < 8) {
                (new Thread(() -> {
                    rwl.read(rwl.readLock);
                })).start();
            } else {
                (new Thread(() -> {
                    rwl.write(rwl.writeLock);
                })).start();
            }
        }

    }
}

