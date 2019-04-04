package com.indi.test;

/**
 * Created by Administrator on 2019/3/20.
 * X维度/Y维度
 *  海尔      小米      华为
 * 笔记本    笔记本    笔记本
 * 手机       手机      手机
 * 手环       手环      手环
 */
//多个维度需要用抽象工厂
public class FactoryTestX {
    public static void main(String[] args) {
        IFactory huaweifactory = new HuaweiFacotry();
        IMobilePhone huaweiphone = huaweifactory.createmobilephone();
        huaweiphone.call();

        IFactory xiaomifactory = new XiaomiFacotry();
        INoteBook xiaominote = xiaomifactory.createnotebook();
        xiaominote.note();
    }
}

class HuaweiFacotry implements IFactory{
    @Override
    public INoteBook createnotebook() {
        precreate();
        return new HuaweiNoteBook();
    }
    @Override
    public IMobilePhone createmobilephone() {
        precreate();
        return new HuaweiMobilePhone();
    }
}

class XiaomiFacotry implements IFactory{
    @Override
    public INoteBook createnotebook() {
        precreate();
        return new XiaomiNoteBook();
    }
    @Override
    public IMobilePhone createmobilephone() {
        precreate();
        return new XiaomiMobilePhone();
    }
}

interface IFactory{
    default void precreate(){
        System.out.println("创建对象前工作");
    }
    INoteBook createnotebook();
    IMobilePhone createmobilephone();
}

interface INoteBook{void note();}
interface IMobilePhone{void call();}
class XiaomiNoteBook implements INoteBook{public void note(){System.out.println("小米笔记本玩游戏");}}
class XiaomiMobilePhone implements IMobilePhone{public void call(){System.out.println("小米手机打电话");}}
class HuaweiNoteBook implements INoteBook{public void note(){System.out.println("华为笔记本玩游戏");}}
class HuaweiMobilePhone implements IMobilePhone{public void call(){System.out.println("华为手机打电话");}}