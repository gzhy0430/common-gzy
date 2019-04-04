package com.indi.design.factory.homework;

/**
 * Created by Administrator on 2019/3/25.
 */
public class SimpleFactory {
    public static void main(String[] args) {
        try {
            IBean iban = BeanFactory.getInstance().createIBean(Bean1X.class);
            iban.dosomething();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}

class BeanFactory{
    private static final BeanFactory INSTANCE = new BeanFactory();
    private BeanFactory(){}
    public static BeanFactory getInstance(){
        return INSTANCE;
    }
    public IBean createIBean(Class<Bean1X> clazz) throws IllegalAccessException, InstantiationException {
        if(clazz == null){
            return null;
        }
        return clazz.newInstance();
    }
}

class Bean1X implements IBean{
    @Override
    public void dosomething() {
        System.out.println("BeanX做一些事" + this);
    }
}

interface IBean{
    void dosomething();
}