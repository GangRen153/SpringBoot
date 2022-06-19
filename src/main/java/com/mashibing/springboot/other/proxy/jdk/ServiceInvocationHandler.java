package com.mashibing.springboot.other.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ServiceInvocationHandler implements InvocationHandler {
    private UserService userService;

    ServiceInvocationHandler(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return (UserService)Proxy.newProxyInstance(UserService.class.getClassLoader(), UserServiceImpl.class.getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(this.userService, args);
        return result;
    }
}
