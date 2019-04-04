package com.indi.test;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Administrator on 2019/3/20.
 * 双重检查单例
 * 静态内部类单例
 * 反射代码和反序列化代码预防
 */

class StaticInnerClassSingleX{
    private StaticInnerClassSingleX(){
        synchronized (StaticInnerClassSingleX.class){
            if(SICSClass.INSTANCE != null){
                throw new RuntimeException("单例对象被侵犯");
            }
        }
    }
    private static class SICSClass{
        private static StaticInnerClassSingleX INSTANCE = new StaticInnerClassSingleX();
    }
    public static StaticInnerClassSingleX getInstance(){
        return SICSClass.INSTANCE;
    }
    void method(){
        System.out.println(Thread.currentThread().getName() + ":" + this.hashCode());
    }
}

class DoubleCheckSingleX implements Serializable {
    private static volatile DoubleCheckSingleX INSTANCE;
    private static boolean flag = false;
    private DoubleCheckSingleX(){
//        防止反射破坏单例
        synchronized (DoubleCheckSingleX.class){
            if(flag){
                throw new RuntimeException("单例模式被侵犯");
            }
            flag = true;
        }
    }
//    防止反序列化破坏单例
    private Object readResolve(){
        return INSTANCE;
    }
    public static DoubleCheckSingleX getInstance(){
        if(INSTANCE == null){
            synchronized(DoubleCheckSingleX.class) {
                if(INSTANCE == null){
                    INSTANCE = new DoubleCheckSingleX();
                }
            }
        }
        return INSTANCE;
    }
    void method(){
        System.out.println(Thread.currentThread().getName() + ":" + this.getClass().getName());
    }
}

public class SingletonTestX {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        DoubleCheckSingleX dsingle1 = DoubleCheckSingleX.getInstance();
//        Class<?> clazz = StaticInnerClassSingleX.class;
//        Constructor<?> c = clazz.getDeclaredConstructor();
//        c.setAccessible(true);
//        StaticInnerClassSingleX ssingle2 = (StaticInnerClassSingleX) c.newInstance();
//        ssingle2.method();
        StaticInnerClassSingleX ssingle3 = StaticInnerClassSingleX.getInstance();
        StaticInnerClassSingleX ssingle1 = StaticInnerClassSingleX.getInstance();
        ssingle1.method();
        ssingle3.method();
//        System.out.println(ssingle1 == ssingle2);
    }
}
