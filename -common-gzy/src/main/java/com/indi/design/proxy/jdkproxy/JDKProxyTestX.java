package com.indi.design.proxy.jdkproxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2019/3/19.
 */

public class JDKProxyTestX {
    public static void main(String[] args) throws Exception {
        Person p = (Person) new JDKMeipo().getInstance(new Girl());
        p.findlove();
//        com.indi.design.proxy.jdkproxy.$Proxy0
//        输出动态字节码到class文件
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Person.class});
        FileOutputStream fos = new FileOutputStream("D:\\workspace\\IdeaProjects\\-common-gzy\\target\\classes\\com\\indi\\design\\proxy\\jdkproxy\\$Proxy0.class");
        fos.write(bytes);
        fos.close();
    }
}

interface Person{
    void findlove();
}

class Girl implements Person{
    public void findlove() {
        System.out.println("要求高富帅");
    }
}

class JDKMeipo implements InvocationHandler{
    private Object target;
    public Object getInstance(Object obj) throws Exception{
        this.target = obj;
        Class<?> clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj = method.invoke(this.target, args);
        return obj;
    }
}