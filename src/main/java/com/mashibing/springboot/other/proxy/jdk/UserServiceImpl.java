package com.mashibing.springboot.other.proxy.jdk;

public class UserServiceImpl implements UserService {
    public UserServiceImpl() {
    }

    public void add() {
        System.out.println("add...");
    }

    public void del() {
        System.out.println("del...");
    }
}