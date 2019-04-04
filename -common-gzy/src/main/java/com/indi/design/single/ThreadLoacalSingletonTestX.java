package com.indi.design.single;

/**
 * Created by Administrator on 2019/3/17.
 * 伪线程安全，线程内线程安全。
 * 注册式单例(容器式)
 */
public class ThreadLoacalSingletonTestX {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                ThreadLocalSingleton singleton = ThreadLocalSingleton.getInstance();
                System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
                System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
                System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
                System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
                System.out.println(Thread.currentThread().getName() + ":" + singleton);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                ThreadLocalSingleton singleton = ThreadLocalSingleton.getInstance();
                System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
                System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
                System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
                System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
                System.out.println(Thread.currentThread().getName() + ":" + singleton);
            }
        });
        t1.start();t2.start();
    }
}

class ThreadLocalSingleton{
    private static final ThreadLocal<ThreadLocalSingleton> INSTANCE =
            new ThreadLocal<ThreadLocalSingleton>(){
        @Override
        protected ThreadLocalSingleton initialValue() {
            return new ThreadLocalSingleton();
        }
    };
    public static ThreadLocalSingleton getInstance(){
        return INSTANCE.get();
    }
}
