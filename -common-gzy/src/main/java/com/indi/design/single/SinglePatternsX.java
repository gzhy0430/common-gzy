package com.indi.design.single;

/**
 * Created by Administrator on 2019/3/8.
 */
public class SinglePatternsX {
    public static void main(String[] orgs){
        SingleTon5.INSTANCE.dosomthing();
    }
}

/**
 * 饿汉式两种(可用)--非延迟加载(lazy-loading)
 */
class SingleTon1{
//    private final static SingleTon1 INSTANCE = new SingleTon1();
    private static SingleTon1 INSTANCE;
    static{
        INSTANCE = new SingleTon1();
    }

    private SingleTon1(){}
    public static SingleTon1 getInstance(){
        return INSTANCE;
    }
}

/**
 * 懒汉式(不可用)-线程不安全
 * 懒汉式(线程安全同步方法)--不推荐使用
 */
class SingleTon2{
    private static SingleTon2 INSTANCE;
    private SingleTon2(){}
    public static synchronized SingleTon2 getInstance(){
    //public static SingleTon2 getInstance(){
        if(INSTANCE == null){
            INSTANCE = new SingleTon2();
        }
        return INSTANCE;
    }
}

/**
 * 双重检查
 */
class SingleTon3{
    private static volatile SingleTon3 INSTANCE;
    private SingleTon3(){}
    public static SingleTon3 getInstatnc(){
        if(INSTANCE == null){
            synchronized (SingleTon3.class){
                if(INSTANCE == null){
                    INSTANCE = new SingleTon3();
                }
            }
        }
        return INSTANCE;
    }
}

/**
 * 静态内部类
 */
class SingleTon4{
    private SingleTon4(){}
    private static class SingleTonInstatnce{
        private static final SingleTon4 INSTANCE = new SingleTon4();
    }
    public static SingleTon4 getInstantce(){
        return SingleTonInstatnce.INSTANCE;
    }
}

/**
 * 枚举
 * */
enum SingleTon5{
    INSTANCE;
    public void dosomthing(){

    }
}
