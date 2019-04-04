package com.indi.design.proxy.homework;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibProxyTestX {
    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "C:\\Users\\Administrator\\Desktop\\cglib_class");
        Person p = (Person) new CGLibMeipo().getInstance(Son.class);
        p.findlove();
    }
}

class CGLibMeipo implements MethodInterceptor {
    public Object getInstance(Class<?> clazz){
        Enhancer enhancer = new Enhancer();//相当于Proxy，代理工具类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);//回调intercept方法
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("织入方法前");
        Object obj = methodProxy.invokeSuper(o, objects);
        System.out.println("织入方法后");
        return obj;
    }
}

abstract class Person{
    abstract void findlove();
}

class Son extends Person {
    public void findlove(){
        System.out.println("要求肤白貌美气质佳");
    }
}
