package com.indi.design.single.homework;

/**
 * Created by Administrator on 2019/3/25.
 */

import java.io.Serializable;

/**
 * 常见单例写法：饿汉式(类加载)、懒汉式(lazy加载)、doubleCheck、静态内部类、枚举
 * 饿汉式：线程安全，非懒加载--SingletonHangry
 * 懒汉式：线程不安全，--参考双重检测单例
 * doubleCheck：线程安全，懒加载--DoubleCheckSingleton
 * 静态内部类：线程安全，懒加载--InnerClassSingleton
 * 枚举：线程安全，非懒加载--LastSingleton
 *
 * 容器式单例：内部维护一个Map，存储类名全路径(key)+对象(value)，使用双重检查机制懒加载并返回线程安全对象--ContailerSingleton
 *
 * ThreadLocal线程内单例：内部维护一个ThreadLocal常量，并重写initvalue方法，线程内直接调用该常量的get方法获取对象--ThreadLocalSingleton
 *
 * 破坏单例的方式:--DefineDestorySingleton
 * 反射破坏单例--反射是通过调用私有化构造器来破坏掉单例的，通过在构造器上加判断，阻止反射破坏单例
 * 反序列化破坏单例--重写readReslove方法
 */

class DefineDestorySingleton implements Serializable{
    private static final DefineDestorySingleton INSTANCE = new DefineDestorySingleton();
    private DefineDestorySingleton(){
        synchronized (DefineDestorySingleton.class){//防止反射破坏单例
            if(INSTANCE != null){
                throw new RuntimeException("不允许破坏单例");
            }
        }
    }
    private Object readResolve(){//防止反序列化破坏单例，实际上已经生成了额外对象，只不过没有用而已
        return INSTANCE;
    }
    public static DefineDestorySingleton getInstance(){
        return INSTANCE;
    }
}

class InnerClassSingleton{
    private InnerClassSingleton(){}
    public static InnerClassSingleton getInstance(){
        return InnerClassSingletonInstance.INSTANCE;
    }
    private static class InnerClassSingletonInstance{
        private static InnerClassSingleton INSTANCE = new InnerClassSingleton();
    }
}

enum LastSingleton{
    INSTANCE;
    public static LastSingleton getInstance(){
        return INSTANCE;
    }
    private Object data;
    public void setData(Object data){
        this.data = data;
    }
    public Object getDate(){
        return data;
    }
}

class DoubleCheckSingleton{
    private static volatile DoubleCheckSingleton INSTANCE = null;
    private DoubleCheckSingleton(){}
    public static DoubleCheckSingleton getInstance(){
        if(INSTANCE == null){
            synchronized (DoubleCheckSingleton.class){
                if(INSTANCE == null){
                    INSTANCE = new DoubleCheckSingleton();
                }
            }
        }
        return INSTANCE;
    }
}

class SingletonHangry{
    private static SingletonHangry INSTANCE = new SingletonHangry();
    private SingletonHangry(){}
    public static SingletonHangry getInstance(){
        return INSTANCE;
    }
}


public class SingletonTestX {
    public static void main(String[] args) {

    }
}
