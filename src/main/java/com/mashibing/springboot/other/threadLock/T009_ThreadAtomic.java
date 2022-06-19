package com.mashibing.springboot.other.threadLock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.LongAdder;

public class T009_ThreadAtomic {
    AtomicInteger atomicInteger = new AtomicInteger(0);
    AtomicIntegerArray integerArray = new AtomicIntegerArray(new int[]{1, 2, 3, 4, 5});
    LongAdder longAdder = new LongAdder();

    public T009_ThreadAtomic() {
    }

    public static void main(String[] args) {
    }
}

