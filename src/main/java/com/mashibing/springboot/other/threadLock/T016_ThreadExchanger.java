package com.mashibing.springboot.other.threadLock;

import java.util.concurrent.Exchanger;

public class T016_ThreadExchanger {
    public T016_ThreadExchanger() {
    }

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger();
        (new Thread(() -> {
            try {
                String value = (String)exchanger.exchange("t1");
                System.out.println("t1 -> " + value);
            } catch (InterruptedException var2) {
                var2.printStackTrace();
            }

        })).start();
        (new Thread(() -> {
            try {
                String value = (String)exchanger.exchange("t2");
                System.out.println("t2 -> " + value);
            } catch (InterruptedException var2) {
                var2.printStackTrace();
            }

        })).start();
    }
}
