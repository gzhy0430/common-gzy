package com.indi.design.observer.homework;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * Created by Administrator on 2019/3/25.
 * https://gper.gupaoedu.com/articleContent?id=686
 * 装饰者和适配器的根本区别
 * 装饰器是is-a关系，不管怎么装饰，被装饰对象不会改变
 * java.io库是最好的装饰例子
 * 装饰者动态的将责任附加到对象身上
 * 适配器是has-a关系，内部包含老系统不变的逻辑，又新增新功能逻辑
 * 适配器主要是为了接口的转换，而装饰器关注的是通过组合来动态的为被装饰着注入新的功能或行为
 * 适配器将一个对象包装起来以改变接口
 * 装饰者将一个对象包装起来以增强新的行为和责任
 * 而外观将一群对象包装起来简化其接口
 *
 * * * Guava API实现GPer社区提问通知的业务场景--两个对象监听同一个对象，按照监听的先后顺序执行。
 * 事件回调方法上加@Subscribe标签，先注册监听者，在发布被监听的消息对象
 */
class GuavaGPer{
    private String name = "咕泡社区";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Subscribe
    public void publishQuestion(Object args){
        Question question = (Question) args;
        System.out.println(question.getUsername() + "在社区发布了问题，内容是：" + question.getContext());
    }
}

class Question{
    private String username;
    private String context;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "Question{" +
                "username='" + username + '\'' +
                ", context='" + context + '\'' +
                '}';
    }
}

class Teacher{
    private GuavaGPer guavaGPer;
    private String name;

    public Teacher(GuavaGPer guavaGPer, String name) {
        this.guavaGPer = guavaGPer;
        this.name = name;
    }

    @Subscribe
    public void update(Object arg){
        Question question = (Question) arg;
        System.out.println(this.name + "您好，您收到一个问题\n来自" + this.guavaGPer.getName() +
                ",提问者是：" + question.getUsername() + ",问题是：" + question.getContext());
    }
}

public class ObserverTestX {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus("咕泡社区");
        GuavaGPer gPer = new GuavaGPer();
        Teacher tom = new Teacher(gPer, "Tom");
        Teacher james = new Teacher(gPer, "James");
        Question question = new Question();
        question.setUsername("李明");
        question.setContext("Spring怎么用");
        eventBus.register(gPer);
        eventBus.register(tom);
//        必须先添加观察者，然后再发布内容，不然通知不到观察者
        eventBus.post(question);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        eventBus.unregister(tom);
        eventBus.register(james);
        question.setUsername("王丽");
        question.setContext("不会写单例");
//        必须先添加观察者，然后再发布内容，不然通知不到观察者
        eventBus.post(question);
    }
}


