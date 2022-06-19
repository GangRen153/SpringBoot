package com.mashibing.springboot.other.threadLock;

import sun.misc.Contended;

public class T004_ThreadFeature {
    public T004_ThreadFeature() {
    }

    public static void main(String[] args) {
        visibility();
        orderly();
    }

    public static void visibility() {
    }

    private static void orderly() {
    }

    public static void atomicity() {
    }

    static class T2 {
        @Contended
        public long value;

        T2() {
        }
    }

    static class T1 {
        public long p1;
        public long p2;
        public long p3;
        public long p4;
        public long p5;
        public long p6;
        public long p7;
        public long p8;

        T1() {
        }
    }
}

