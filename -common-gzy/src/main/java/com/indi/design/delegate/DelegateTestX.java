package com.indi.design.delegate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/3/20.
 * 委派模式
 */
class Boss{
    public void command(String cmd, Leader leader){
        leader.doing(cmd);
    }
}

class Leader{
//    预先知道每个员工的特征，分发任务
    private Map<String, IEmployee> register = new HashMap<>();
    public Leader(){
        register.put("加密", new EmployeeA());
        register.put("架构", new EmployeeB());
    }
    public void doing(String cmd){
        register.get(cmd).doing(cmd);
    }
}

class EmployeeB implements IEmployee{
    @Override
    public void doing(String cmd) {
        System.out.println("我是员工B，擅长架构，执行:" + cmd);
    }
}

class EmployeeA implements IEmployee{
    @Override
    public void doing(String cmd) {
        System.out.println("我是员工A，擅长加密，执行:" + cmd);
    }
}

interface IEmployee{
    public void doing(String cmd);
}

public class DelegateTestX {
    public static void main(String[] args) {
        new Boss().command("架构", new Leader());
    }
}
