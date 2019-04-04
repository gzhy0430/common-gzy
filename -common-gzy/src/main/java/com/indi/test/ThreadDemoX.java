package com.indi.test;


import java.util.concurrent.ExecutionException;

public class ThreadDemoX {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        T2 t = new T2();
        for(int i = 0; i < 5; i++){
            new Thread(()->{
                for(int j = 0; j < 1000; j++){
                    t.increase();
                }
            }).start();
        }

//        while(Thread.activeCount()>1)  //保证前面的线程都执行完
//            Thread.yield();
        System.out.println(t.i);
    }
}

class T2 {
    public volatile int i = 0;
    public void increase() {
        i++;
    }
}