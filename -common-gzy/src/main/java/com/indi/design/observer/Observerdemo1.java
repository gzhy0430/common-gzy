package com.indi.design.observer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 传统的线程间通信方式：
 * 共享内存方式：共享对象的同步方法、共享对象轮询监视、wait\notify机制
 * 消息通信方式：java.io.PipedInputStream\java.io.PipedOutoutStream
 */

public class Observerdemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService excutor = Executors.newCachedThreadPool();
        ChildX childX = new ChildX("小萝莉");
        childX.addListener(new DadChildCryListener());
        childX.addListener(new MamaChildCryListener());
//        FutureTask future = new FutureTask(childX);
//        excutor.submit(future);
        Future future = excutor.submit(childX);
        excutor.shutdown();
        Thread.sleep(1000);
        Object o = future.get();
        System.out.println(o);
    }
}

class ChildCryEvent{

}

//小孩哭泣监听器
interface ChildCryListener{
    void warmbaby(ChildCryEvent event);
}

class ChildX implements Callable {
    private String name;
    private List<ChildCryListener> listeners = new LinkedList();

    public ChildX(String name) {
        this.name = name;
    }

    void cry() throws InterruptedException {
        System.out.println(this.name + "哭了");
        for(ChildCryListener listener : listeners){
            Thread.sleep(1000);
            listener.warmbaby(new ChildCryEvent());
        }
    }

    public void addListener(ChildCryListener listener){
        this.listeners.add(listener);
    }
    @Override
    public Object call() {
        try {
            Thread.sleep(1000);
            cry();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public String toString() {
        return name;
    }
}

class DadChildCryListener implements ChildCryListener{
    @Override
    public void warmbaby(ChildCryEvent event) {
        System.out.println("父亲换尿不湿");
    }
}

class MamaChildCryListener implements ChildCryListener{
    @Override
    public void warmbaby(ChildCryEvent event) {
        System.out.println("妈妈喂奶");
    }
}