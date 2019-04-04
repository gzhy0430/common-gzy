package com.indi.design.strategy.homework;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Administrator on 2019/3/25.
 * https://gper.gupaoedu.com/articleContent?id=671
 * Spring源码中常见的委派模式，UML图
 * 利用策略模式重构一段代码
 */
public class StrategyTestX {
    public static void main(String[] args) {
        ProvenceDrowMap pdm1 = new ProvenceDrowMap(DrowMapStrategy.getDrowMap("HEBEI"));pdm1.excute();
        ProvenceDrowMap pdm2 = new ProvenceDrowMap(DrowMapStrategy.getDrowMap("GUANGDONG"));pdm2.excute();
        ProvenceDrowMap pdm3 = new ProvenceDrowMap(DrowMapStrategy.getDrowMap("SHANGXI"));pdm3.excute();
    }
}

class ProvenceDrowMap{
    private DrowMap drowMap;
    public ProvenceDrowMap(DrowMap drowMap) {
        this.drowMap = drowMap;
    }
    public void excute(){
        this.drowMap.drowmap();
    }
}

interface DrowMap{
    void drowmap();
}

//原业务逻辑，31个省份执行不同的逻辑，采用switch-case-break或者if-elseif方式
class HebeiDrowMap implements DrowMap{
    @Override
    public void drowmap() {
        System.out.println("画河北地图");
    }
}

class GuangdongDrowMap implements DrowMap{
    @Override
    public void drowmap() {
        System.out.println("画广东地图");
    }
}

class ShangxiDrowMap implements DrowMap{
    @Override
    public void drowmap() {
        System.out.println("画山西地图");
    }
}

class DrowMapStrategy{
    private static final ConcurrentMap<String, DrowMap> STATEGY = new ConcurrentHashMap<>();
    static{
        STATEGY.put(ProvenceArr.HEBEI.toString(), new HebeiDrowMap());
        STATEGY.put(ProvenceArr.GUANGDONG.toString(), new GuangdongDrowMap());
        STATEGY.put(ProvenceArr.SHANGXI.toString(), new ShangxiDrowMap());
    }
    private enum ProvenceArr{
        HEBEI, GUANGDONG, SHANGXI;
    }
    public static DrowMap getDrowMap(String s){
        return STATEGY.get(s);
    }
}

