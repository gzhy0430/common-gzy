package com.indi.design.observer;

import java.util.Observable;
import java.util.Observer;

//    JDK提供的观察者实现方式--可以被观察
class GPer extends Observable {
    private String name = "咕泡生态圈";
    private static GPer gper = null;
    private GPer(){}
    public static GPer getInstance(){
        if(null == gper){
            gper = new GPer();
        }
        return gper;
    }

    public String getName() {
        return name;
    }

    public void publishQuestion(Question question){
        System.out.println(question.getUsername() + ":在" + this.name + "上发布了问题：" + question.getContent());
        setChanged();
        notifyObservers(question);
    }
}

class Question{
    private String username;
    private String content;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

//观察者--异步通知
class Teacher implements Observer{
    private String name;
    public Teacher(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        GPer gPer = (GPer) o;
        Question question = (Question) arg;
        System.out.println("==========================");
        System.out.println(name + "老师，您好！\n您收到了一个来自" + gPer.getName() + "的提问" +
                "，问题内容如下：" + question.getContent() + "\n提问这是" + question.getUsername());
    }
}

public class GPerTestX {
    public static void main(String[] args) {
        GPer gper = GPer.getInstance();

        Teacher tom = new Teacher("tom");

//        需要@Tom老师
        Question q = new Question();
        q.setUsername("Tom");
        q.setContent("不会写Java");

        gper.addObserver(tom);

        gper.publishQuestion(q);
    }
}
