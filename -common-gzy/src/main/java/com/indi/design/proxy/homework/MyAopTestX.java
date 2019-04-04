package com.indi.design.proxy.homework;

import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2019/3/26.
 * --动态代理实现业务控制事务
 * transaction
 */
public class MyAopTestX {
    public static void main(String[] args) {
        IDao dao = (IDao) ProxyTool.getProxyInstance(new MyDao(), new MyAspact());
        dao.dosomthing();
    }
}

class ProxyTool{
    public static Object getProxyInstance(final Object target, final MyAspact aop){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), (proxy, method, args)->{
            aop.began();
            Object result = method.invoke(target, args);
            aop.end();
            return result;
        });
    }
}

class MyAspact{
    void began(){
        System.out.println("开始事务");
    }
    void end(){
        System.out.println("事务结束");
    }
}

class MyDao implements IDao{
    @Override
    public void dosomthing() {
        System.out.println("主方法");
    }
}

interface IDao{
    void dosomthing();
}