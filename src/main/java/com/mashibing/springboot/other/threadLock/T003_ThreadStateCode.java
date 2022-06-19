package com.mashibing.springboot.other.threadLock;

public class T003_ThreadStateCode {
    public T003_ThreadStateCode() {
    }

    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.interrupt();
        thread.isInterrupted();
        Thread.interrupted();
        thread.stop();
        thread.suspend();
        thread.resume();
    }
}