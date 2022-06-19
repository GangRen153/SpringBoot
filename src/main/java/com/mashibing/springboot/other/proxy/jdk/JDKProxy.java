package com.mashibing.springboot.other.proxy.jdk;

import java.io.FileOutputStream;
import sun.misc.ProxyGenerator;

public class JDKProxy {
    public JDKProxy() {
    }

    public static void main(String[] args) throws Exception {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", true);
        UserService userService = new UserServiceImpl();
        ServiceInvocationHandler handler = new ServiceInvocationHandler(userService);
        UserService service = handler.getUserService();
        byte[] proxies = ProxyGenerator.generateProxyClass("$UserServiceProxy", UserServiceImpl.class.getInterfaces());
        FileOutputStream fos = new FileOutputStream("C:\\test\\a.class");
        fos.write(proxies);
        fos.flush();
        fos.close();
        service.add();
    }
}
