package com.mashibing.springboot.other.queue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class T005_ThreadPriorityQueue {
    public T005_ThreadPriorityQueue() {
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue();
        queue.add(3);
        queue.add(7);
        queue.add(2);
        queue.add(44);
        queue.add(1);
        queue.add(23);
        Integer peek = (Integer)queue.peek();
        System.out.println(peek);
        PriorityQueue<User> queue1 = new PriorityQueue(new User());
        queue1.add(new User(3));
        queue1.add(new User(7));
        queue1.add(new User(2));
        queue1.add(new User(44));
        queue1.add(new User(1));
        queue1.add(new User(23));
        User user = (User)queue1.peek();
        System.out.println(user.age);
    }

    static class User implements Comparator<User> {
        int age;

        public User() {
        }

        public User(int age) {
            this.age = age;
        }

        public int compare(User o1, User o2) {
            return o1.age - o2.age;
        }
    }
}
