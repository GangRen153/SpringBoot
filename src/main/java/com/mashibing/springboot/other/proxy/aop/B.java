package com.mashibing.springboot.other.proxy.aop;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class B implements BImpl {
    private String name;
    @Autowired
    public AImpl a;
    static HashMap map = new HashMap();

    public B() {
    }

    public static void main(String[] args) {
        new ArrayList();
        int id = true;
        map.put(1, 1);
        char a = 'a';
        char b = 98;
        char d = (char)(a + b);
        System.out.println(d);
        char c = 195;
        System.out.println(c);
        a = (char)(a + b);
    }
}
