package com.indi.test;

import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2019/3/20.
 * AOP原理本质
 */
class AspactAOP{
    void begain(){
        System.out.println("开始事务");
    }
    void end(){
        System.out.println("结束事务");
    }
}

interface UserDao{
    void save();
}

class UserDaoImpl implements UserDao {
    public void save(){
        System.out.println("保存对象方法");
    }
}


public class ProxyTestX {
//    维护目标对象
    private static Object target;

//    维护关键点代码的类
    private static AspactAOP aop;
    public static void main(String[] args) {
        UserDao dao = (UserDao) getProxyInstance(new UserDaoImpl(), new AspactAOP());
        dao.save();
    }
    static Object getProxyInstance(final Object target_, final AspactAOP aop_){
        target = target_;
        aop = aop_;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),(proxy, method, args)->{
            aop.begain();
            Object result = method.invoke(target, args);
            aop.end();
            return result;
        });
    }
}
