package com.indi.design.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

/**
 * Created by Administrator on 2019/3/15.
 * 简单工厂
 */
public class SimpleFactoryTestX {
    public static void main(String[] args) {
        SimpleFacotryX sf = new SimpleFacotryX();
//        ICourse course = sf.create("java");
//        ICourse course = sf.create("com.indi.design.JavaCourse");
        ICourse course = sf.create(JavaCourse.class);
        course.record();

//        Calendar c = Calendar.getInstance();
//        Logger logger = LoggerFactory.getLogger(SimpleFactoryTestX.class);
//        logger.info("!@#");
    }
}

class SimpleFacotryX{
    public ICourse create(Class clazz){
        try{
            if(null == clazz)
                return null;
            return (ICourse)clazz.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /*public ICourse create(String className){
        try{
            if(null == className || "".equals(className))
                return null;
            return (ICourse) Class.forName(className).newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }*/
    /*public ICourse create(String name){
        if("java".equals(name)){
            return new JavaCourse();
        }else{
            return null;
        }
    }*/
}

/**
 * 课程接口
 */
interface ICourse{
    public void record();
}
class JavaCourse implements  ICourse{
    @Override
    public void record() {
        System.out.println("录制Java课程");
    }
}