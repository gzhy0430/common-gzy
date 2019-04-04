package com.indi.design.factory.abstractfactory;

/**
 * Created by Administrator on 2019/3/15.
 * 要求所有的工厂都实现这个工厂
 * 不符合开闭原则
 */
public class ICourseFactoryTestX {
    public static void main(String[] args) {
        IFactoryX factory = new JavaFactory();
        factory.createCourse().record();
        factory.createNode().note();
    }
}

interface IFactoryX{
    ICourse createCourse();
    INote createNode();
}

class JavaFactory implements IFactoryX{

    @Override
    public ICourse createCourse() {
        return new JavaCourse();
    }

    @Override
    public INote createNode() {
        return new JavaNote();
    }
}

/**
 * 笔记接口
 */
interface INote{
    public void note();
}
class JavaNote implements  INote{
    @Override
    public void note() {
        System.out.println("录制Java笔记");
    }
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