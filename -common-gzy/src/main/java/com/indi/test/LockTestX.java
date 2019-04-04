package com.indi.test;

/**
 * Created by Administrator on 2019/3/14.
 */
public class LockTestX {
    static int i = 0;

    void demo1(){
        synchronized (this){

        }
    }

    void demo2(){
        synchronized (LockTestX.class){
            try {
                Thread.sleep(50000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static  void demo(){
        i++;
    }

    public static void main(String[] args) {
        new Thread(()->{
            LockTestX lt = new LockTestX();
            lt.demo2();
        }).start();
        new Thread(()->{
            LockTestX lt2 = new LockTestX();
            lt2.demo1();
        }).start();
    }

}
