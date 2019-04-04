package com.indi.design.proxy.homework.myprxy;

/**
 * Created by Administrator on 2019/3/25.
 * 仿JDK动态代理实现原理手写
 * 为什么JDK动态代理目标类实现接口数量不能超过65535--Class<?> cl = getProxyClass0(loader, intfs);--proxy.txt
 * 用代理重构自身业务--MyAopTestX
 */
public class MyProxyTestX {
    public static void main(String[] args) {
        try{
            Person obj = (Person) new MyMeipo().getInstance(new Girl());
            System.out.println(obj.getClass());
            obj.findlove();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
