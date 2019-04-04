package com.indi.design.single;

import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.context.annotation.Lazy;

import java.io.*;
import java.lang.reflect.Constructor;

/**
 * Created by Administrator on 2019/3/16.
 */
public class SingletonPatternTestX {
    public static void main(String[] args) throws Exception{
        /*Thread t1 = new Thread(new ExecutorThread());
        Thread t2 = new Thread(new ExecutorThread());
        t1.start();t2.start();
        System.out.println("Executor End");*/

//        反射破坏单例
       /* Class<?> clazz = LazyInnerClassSingleton.class;
        Constructor c = clazz.getDeclaredConstructor(null);
        c.setAccessible(true);//强吻
        Object o1 = c.newInstance();*/
//        反序列化破坏单例
//        Object o2 = LazyInnerClassSingleton.getInstance();
        EnumSingleton o2 = EnumSingleton.getInstance();
        o2.setData("123");
//        LazyInnerClassSingleton o1 = null;
        EnumSingleton o1 = null;
        FileOutputStream fos = new FileOutputStream("LazyInnerClassSingleton.obj");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(o2);
        oos.flush();oos.close();
        FileInputStream fis = new FileInputStream("LazyInnerClassSingleton.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        o1 = (EnumSingleton) ois.readObject();
        System.out.println(o1.getData());
        System.out.println(o2.getData());
        System.out.println(o1.getData() == o2.getData());
   }
}

//从JDK层面就为枚举不被反序列化和反射破坏来保驾护航
enum EnumSingleton{

    INSTANCE;
    private Object data;
    public Object getData(){
        return data;
    }
    public void setData(Object data){
        this.data = data;
    }
    public static EnumSingleton getInstance(){return INSTANCE;}
}

/*
 * 没有用到synchronized关键字
 * LazyHolder里面的逻辑需要等到外部方法调用时候才执行，利用了内部类特性，JVM底层执行逻辑，避免了线程安全问题
 * 性能最优的写法
 * 作业：理解内部类执行逻辑
 * 缺点：容易被反射攻击--解决方式，构造加判断
 * 重写readResolve方法解决反序列化
 */
class LazyInnerClassSingleton implements Serializable{
    private LazyInnerClassSingleton(){
        if(LazyHolder.INSTANCE != null){
            throw new RuntimeException("不允许构建多个实例!");
        }
    }
    private static class LazyHolder{
//        懒汉式单例
        private static LazyInnerClassSingleton INSTANCE = new LazyInnerClassSingleton();
    }
    public static LazyInnerClassSingleton getInstance(){
        return LazyHolder.INSTANCE;
    }

//    重写readResolve方法，只不过覆盖了反序列化出来的对象
//    还是创建了两次，发生在JVM层面，相对来说安全
//    之前反序列化出来的对象会被GC回收
    private Object readResolve(){
        return LazyHolder.INSTANCE;
    }
}

class DoubleCheckSingleton{
    private static volatile DoubleCheckSingleton INSTATNCE = null;
    private DoubleCheckSingleton(){}
    public static DoubleCheckSingleton getInstance(){
        if (INSTATNCE == null){
            synchronized (DoubleCheckSingleton.class){
                if(INSTATNCE == null){
                    INSTATNCE = new DoubleCheckSingleton();
//                    CPU执行时候会转换为JVM指令：
//                    指令重排序
//                    1.分配内存给对象
//                    2.初始化对象
//                    3.将初始化好的对象和内存地址建立关联
//                    4.用户初次使用
                }
            }
        }
        return INSTATNCE;
    }
}

class ExecutorThread implements  Runnable{
    @Override
    public void run() {
        DoubleCheckSingleton singleton = DoubleCheckSingleton.getInstance();
//        LazySingleton singleton = LazySingleton.getInstance();
//        System.out.println(Thread.currentThread().getName() + ":" + singleton);
        System.out.println(Thread.currentThread().getName() + ":" + singleton);
    }
}

class LazySingleton{
    private static LazySingleton INSTATNCE = null;
    private LazySingleton(){}

//    JDK1.8之后对synchronized之后优化了不少，不可避免的还是存在一定性能问题
    public synchronized static LazySingleton getInstance(){
        if(INSTATNCE == null){
            INSTATNCE = new LazySingleton();
        }
        return INSTATNCE;
    }
}

class HangrySingleton{
//    private static final HangrySingleton INSTANCE = new HangrySingleton();
    private static final HangrySingleton INSTANCE;
    static{
        INSTANCE = new HangrySingleton();
    }

    private HangrySingleton(){}
    public static HangrySingleton getInstance(){
        return INSTANCE;
    }
}

