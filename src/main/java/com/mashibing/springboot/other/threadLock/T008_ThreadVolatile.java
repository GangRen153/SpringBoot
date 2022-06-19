package com.mashibing.springboot.other.threadLock;

public class T008_ThreadVolatile {
    volatile int count = 0;

    public T008_ThreadVolatile() {
    }

    public void add() {
        for(int i = 0; i < 10000; ++i) {
            ++this.count;
        }

    }

    public void addSync() {
        for(int i = 0; i < 10000; ++i) {
            Class var2 = T008_ThreadVolatile.class;
            synchronized(T008_ThreadVolatile.class) {
                ++this.count;
            }
        }

    }

    public static void main(String[] args) throws Exception {
        T008_ThreadVolatile threadVolatile = new T008_ThreadVolatile();
        threadVolatile.getClass();
        Thread t1 = new Thread(threadVolatile::add);
        threadVolatile.getClass();
        Thread t2 = new Thread(threadVolatile::add);
        t1.start();
        t2.start();
        Thread.sleep(2000L);
        System.out.println(threadVolatile.count);
    }
}
