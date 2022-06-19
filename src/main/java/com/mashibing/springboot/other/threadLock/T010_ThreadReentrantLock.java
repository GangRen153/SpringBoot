package com.mashibing.springboot.other.threadLock;

import java.util.concurrent.locks.ReentrantLock;

public class T010_ThreadReentrantLock {
    ReentrantLock lock = new ReentrantLock();
    boolean isLock = false;

    public T010_ThreadReentrantLock() {
    }

    void m1() {
        try {
            this.lock.lock();

            for(int i = 0; i < 5; ++i) {
                System.out.println("m1:..." + i);
                Thread.sleep(100L);
            }
        } catch (Exception var5) {
        } finally {
            this.lock.unlock();
        }

    }

    void m2() {
        this.isLock = this.lock.tryLock();
        System.out.println(this.isLock);

        for(int i = 0; i < 5; ++i) {
            System.out.println("m2:..." + i);
        }

        if (this.isLock) {
            this.lock.unlock();
        }

    }

    void m3() {
        try {
            boolean b = this.lock.tryLock();
            if (!b) {
                this.lock.unlock();
            }

            this.lock.lockInterruptibly();

            for(int i = 0; i < 5; ++i) {
                Thread.sleep(300L);
                System.out.println("m3:..." + i);
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        } finally {
            this.lock.unlock();
        }

    }

    void m4() {
        try {
            this.lock.lock();
            System.out.println(this.lock.isLocked());
            this.lock.lock();
            System.out.println(this.lock.isLocked());

            for(int i = 0; i < 5; ++i) {
                Thread.sleep(500L);
                System.out.println("m3:..." + i);
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        } finally {
            this.lock.unlock();
        }

    }

    public static void main(String[] args) throws Exception {
        T010_ThreadReentrantLock rl = new T010_ThreadReentrantLock();
        new Thread(rl::m1);
        new Thread(rl::m2);
        new Thread(rl::m3);
        new Thread(rl::m3);
        rl.getClass();
        Thread t5 = new Thread(rl::m4);
        t5.start();
        System.in.read();
    }
}
