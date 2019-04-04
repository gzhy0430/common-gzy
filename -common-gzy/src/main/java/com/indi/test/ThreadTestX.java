package com.indi.test;

/**
 * Created by Administrator on 2019/3/10.
 */
public class ThreadTestX {
    private static boolean stop = false;
    public static int count = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            int i = 0;
            while(!stop){
                i++;
                System.out.println(i);
            }
        });
        t.start();
        Thread.sleep(1000);
        stop = true;
    }
    public static void inc(){
    }
}
