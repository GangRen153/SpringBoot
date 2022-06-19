package com.mashibing.springboot.other.threadLock;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class T013_ThreadPhaser {
    static Random r = new Random();
    static MarriagePhaser phaser = new MarriagePhaser();

    public T013_ThreadPhaser() {
    }

    static void milliSleep(int milli) {
        try {
            TimeUnit.MILLISECONDS.sleep((long)milli);
        } catch (InterruptedException var2) {
            var2.printStackTrace();
        }

    }

    public static void main(String[] args) {
        phaser.bulkRegister(7);

        for(int i = 0; i < 5; ++i) {
            (new Thread(new Person("p" + i))).start();
        }

        (new Thread(new Person("����"))).start();
        (new Thread(new Person("����"))).start();
    }

    static class Person implements Runnable {
        String name;

        public Person(String name) {
            this.name = name;
        }

        public void arrive() {
            T013_ThreadPhaser.milliSleep(T013_ThreadPhaser.r.nextInt(1000));
            System.out.printf("%s �����ֳ���\n", this.name);
            T013_ThreadPhaser.phaser.arriveAndAwaitAdvance();
        }

        public void eat() {
            T013_ThreadPhaser.milliSleep(T013_ThreadPhaser.r.nextInt(1000));
            System.out.printf("%s ����!\n", this.name);
            T013_ThreadPhaser.phaser.arriveAndAwaitAdvance();
        }

        public void leave() {
            T013_ThreadPhaser.milliSleep(T013_ThreadPhaser.r.nextInt(1000));
            System.out.printf("%s �뿪��\n", this.name);
            T013_ThreadPhaser.phaser.arriveAndAwaitAdvance();
        }

        private void hug() {
            if (!this.name.equals("����") && !this.name.equals("����")) {
                T013_ThreadPhaser.phaser.arriveAndDeregister();
            } else {
                T013_ThreadPhaser.milliSleep(T013_ThreadPhaser.r.nextInt(1000));
                System.out.printf("%s ������\n", this.name);
                T013_ThreadPhaser.phaser.arriveAndAwaitAdvance();
            }

        }

        public void run() {
            this.arrive();
            this.eat();
            this.leave();
            this.hug();
        }
    }

    static class MarriagePhaser extends Phaser {
        MarriagePhaser() {
        }

        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("�����˵����ˣ�" + registeredParties);
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("�����˳����ˣ�" + registeredParties);
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("�������뿪�ˣ�" + registeredParties);
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("����������������ﱧ����" + registeredParties);
                    return true;
                default:
                    return true;
            }
        }
    }
}

