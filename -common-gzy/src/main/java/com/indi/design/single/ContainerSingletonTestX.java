package com.indi.design.single;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2019/3/17.
 */
public class ContainerSingletonTestX {
    public static void main(String[] args) {
        Object o = ContailerSingleton.getBean("com.indi.design.single.SingletonPatternTestX");
        System.out.println(o);
    }
}

class ContailerSingleton{
    private ContailerSingleton(){}

    private static Map<String, Object> ioc = new ConcurrentHashMap<String, Object>();

    public static Object getBean(String className){
        if(!ioc.containsKey(className)){
            synchronized (ioc){
                if(!ioc.containsKey(className)){
                    Object obj = null;
                    try {
                        obj = Class.forName(className).newInstance();
                        ioc.put(className, obj);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return obj;
                }
            }
        }
        return ioc.get(className);
    }
}