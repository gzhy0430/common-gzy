package com.indi.design.decorator.v1;

/**
 * Created by Administrator on 2019/3/17.
 */
public interface DecoratorPatternTestX {
    public static void main(String[] args) {
        Battercake b1 = new Battercake();
        System.out.println(b1.getMsg() + ":" + b1.price());
        Battercake b2 = new BattercakeWithEgg();
        System.out.println(b2.getMsg() + ":" + b2.price());
        Battercake b3 = new BattercakeWithEggAndSausage();
        System.out.println(b3.getMsg() + ":" + b3.price());

    }
}

class Battercake{
    protected  String getMsg(){
        return "煎饼";
    }
    protected  int price(){
        return 5;
    }
}

class BattercakeWithEggAndSausage extends BattercakeWithEgg{
    @Override
    protected String getMsg() {
        return super.getMsg() + "加一个香肠";
    }

    @Override
    protected int price() {
        return super.price() + 2;
    }
}

class BattercakeWithEgg extends Battercake{
    @Override
    protected String getMsg() {
        return super.getMsg() + "1个鸡蛋";
    }

//    加一个鸡蛋加1块钱
    @Override
    protected int price() {
        return super.price() + 1;
    }
}