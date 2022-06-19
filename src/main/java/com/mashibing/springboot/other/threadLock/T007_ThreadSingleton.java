package com.mashibing.springboot.other.threadLock;

public class T007_ThreadSingleton {
    public T007_ThreadSingleton() {
    }

    static class Singleton3 {
        private volatile Singleton3 singleton3 = null;

        private Singleton3() {
        }

        public Singleton3 getInstance() {
            if (this.singleton3 == null) {
                Class var1 = Singleton3.class;
                synchronized(Singleton3.class) {
                    if (this.singleton3 == null) {
                        this.singleton3 = new Singleton3();
                    }
                }
            }

            return this.singleton3;
        }
    }

    static class Singleton2 {
        private Singleton2 singleton2 = null;

        private Singleton2() {
        }

        public synchronized Singleton2 getInstance() {
            if (this.singleton2 == null) {
                this.singleton2 = new Singleton2();
            }

            return this.singleton2;
        }
    }

    static class Singleton1 {
        private Singleton1 singleton1 = new Singleton1();

        private Singleton1() {
        }

        public Singleton1 getInstance() {
            return this.singleton1;
        }
    }
}
