package com.indi.design.proxy;

public class StaticProxyTestX {
    public static void main(String[] args) {
        Father f = new Father(new Son());
        f.findlove();

//        每天都在用的一种静态代理
//        数据库进行分库分表
//        ThreadLocal
//        进行数据源动态切换

    }
}

interface Person{
    void findlove();
}

class Father{
    private Son son;

    public Father(Son son) {
        this.son = son;
    }

    public void findlove(){
        System.out.println("父亲物色对象");
        son.findlove();
        System.out.println("双方同意，确立关系！");
    }
}

class Son implements Person{
    public void findlove(){
        System.out.println("要求肤白貌美气质佳");
    }
}