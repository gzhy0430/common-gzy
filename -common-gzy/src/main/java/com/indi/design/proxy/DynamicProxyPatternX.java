package com.indi.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2019/3/14.
 * 动态代理演示
 * 使用步骤：新建一个接口->为接口创建一个实现类->创建代理类实现java.lang.reflect.InvocationHandler接口
 */
public class DynamicProxyPatternX {
    public static void main(String[] args) {
//        保存生成的代理类的字节码文件
//        JDK动态代理
        Subject subject = new JDKDynamicProxy(new RealSubject()).getProxy();
        subject.dosomething();
    }
}

//JDK代理类实现JDKDynamicProxy
class JDKDynamicProxy implements InvocationHandler{
    private Object target;

    public JDKDynamicProxy(Object target) {
        this.target = target;
    }

//    获取被代理接口实例对象
    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Do something before");
        Object result = method.invoke(target, args);
        System.out.println("Do something after");
        return result;
    }
}

//创建一个接口-抽象主题接口
interface Subject{
    void dosomething();
}

//新建实现类-真实主题类
class RealSubject implements Subject{
    @Override
    public void dosomething() {
        System.out.println("RealSubject do something");
    }
}

