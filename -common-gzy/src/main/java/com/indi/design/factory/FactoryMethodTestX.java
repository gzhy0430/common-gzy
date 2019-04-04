package com.indi.design.factory;

/**
 * Created by Administrator on 2019/3/15.
 * 工厂方法
 */
public class FactoryMethodTestX {
    public static void main(String[] args) {
        ICourseFactoryX factory = new JavaCrouseFactory();
        ICourse course = factory.create();
        course.record();
    }
}

//interface ICourseFactoryX{
abstract class ICourseFactoryX{
    public void preCreate(){
        System.out.println("做一些实例化前工作");
    }
//    ICourse create();
    abstract ICourse create();
}

//class JavaCrouseFactory implements ICourseFactoryX{
class JavaCrouseFactory extends ICourseFactoryX{
    @Override
    public ICourse create() {
        preCreate();
        return new JavaCourse();
    }
}