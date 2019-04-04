package com.indi.design.tmplate;

/**
 * Created by Administrator on 2019/3/22.
 * 模板会有一个或者多个未实现的方法，而且这几个未实现方法有固定的执行顺序
 */

public class TemplateMethodTestX {
    public static void main(String[] args) {
        System.out.println("JAVA架构师课程");
        NetworkCourse javacourse = new JavaCourse();
        javacourse.createCourse();

        System.out.println("大数据课程");
        NetworkCourse bigDataCourse = new BigDataCourse(true);
        bigDataCourse.createCourse();
    }
}

class BigDataCourse extends NetworkCourse{

    private boolean needHomeWorkFlag;
    BigDataCourse(boolean needHomeWorkFlag){
        this.needHomeWorkFlag = needHomeWorkFlag;
    }

    @Override
    protected boolean needHomeWork() {
        return this.needHomeWorkFlag;
    }

    @Override
    protected void checkHomeWork() {
        System.out.println("检查大数据作业");
    }
}

class JavaCourse extends NetworkCourse{
    @Override
    protected void checkHomeWork() {
        System.out.println("检查java作业");
    }
}

abstract class NetworkCourse{
    protected final void createCourse(){
//        发布预习资料
        this.postPreResource();
//        在线直播
        this.liveVideo();
//        提交源码
        this.postSource();
//        布置作业，有些课没有作业，有些课有作业
//        如果有作业，检查作业，如果没有作业，end
        if(needHomeWork()){
            checkHomeWork();
        }
    }

    protected abstract void checkHomeWork();

//    钩子方法，实现流程的微调
    protected boolean needHomeWork(){
        return false;
    }

    final void postSource(){
        System.out.println("提交源代码");
    }

    final void liveVideo(){
        System.out.println("直播授课");
    }

    final void postPreResource(){
        System.out.println("分发预习资料");
    }
}
