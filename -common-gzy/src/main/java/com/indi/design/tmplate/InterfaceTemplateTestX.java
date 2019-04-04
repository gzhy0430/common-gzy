package com.indi.design.tmplate;

public class InterfaceTemplateTestX {
    public static void main(String[] args) {
        DefaultInterface cbc = new MkCabbagefoodsImpl();
        cbc.mkfood();
        DefaultInterface cro = new MkMeatfoodsImpl();
        cro.mkfood();
    }
}

class MkCabbagefoodsImpl implements DefaultInterface{//炒白菜
    @Override
    public void buymarterile() {//买材料
        System.out.println("买大白菜");
    }
    @Override
    public void operatemarterile() {//处理材料
        System.out.println("切白菜，清洗。。。");
    }
    @Override
    public void invokefood() {//做菜
        System.out.println("一点点油，放入白菜，加酱油，盐，盖盖子，焖至滚熟");
    }
}

class MkMeatfoodsImpl implements DefaultInterface{//做肉菜
    @Override
    public void buymarterile() {//买材料
        System.out.println("买肉类材料");
    }
    @Override
    public void operatemarterile() {//处理材料
        System.out.println("切肉，放姜，放料酒去腥味。。。");
    }
    @Override
    public void invokefood() {//做菜
        System.out.println("一点点油，一块块放肉，煎至七成熟");
    }
}

interface DefaultInterface{
    default void mkfood(){
        buymarterile();//买材料
        fishmathings();//清洗工具
        operatemarterile();//处理材料
        invokefood();//做菜
    }
    void operatemarterile();
    void invokefood();
    default void fishmathings(){
        System.out.println("清洗锅碗瓢盆");
    }
    void buymarterile();
}
