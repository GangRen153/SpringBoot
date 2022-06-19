package com.mashibing.springboot.other.proxy.cglib;

import java.lang.reflect.Method;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor {
    public CglibProxy() {
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before method invoke");
        methodProxy.invokeSuper(o, objects);
        System.out.println("after method invoke");
        return o;
    }

    public static void main(String[] args) {
        System.setProperty("cglib.debugLocation", "C:\\GangRen\\Java\\personalProject\\ideaProjects\\SpringBoot\\src\\main\\java\\com\\mashibing\\springboot\\proxy\\cglib");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(new CglibProxy());
        UserServiceImpl userService = (UserServiceImpl)enhancer.create();
        userService.add();
        userService.del();
    }
}