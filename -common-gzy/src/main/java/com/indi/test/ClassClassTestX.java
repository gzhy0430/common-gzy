package com.indi.test;

/**
 * Created by Administrator on 2019/3/11.
 */
public class ClassClassTestX {
    String s = SubClassSS.s;
    public static void main(String[] args) throws ClassNotFoundException {
        /*
        对于静态字段-只有直接定义这个字段的类才会被初始化
        子类引用父类定义的静态字段，只会触发父类的初始化操作；
         */
//        System.out.println(SubClassSS.v);

        System.out.println(SubClassSS.s);
        /*ClassLoader c = new ClassLoader() {
        };
        c.loadClass("");*/
    }
}
class SSClass{
    static{
        System.out.println("AAA");
//        System.out.println(v);
//        v = 12345;
    }
    public static int v;
}

class SuperClassSSC extends SSClass{
    static{
        System.out.println("Superinit");
    }
    public static int v = 123;
    public SuperClassSSC(){System.out.println("init SuperClass");}
}

class SubClassSS extends SuperClassSSC{
    public static final String s = "Consant";
    static{
        System.out.println("Subinit");
    }
    static int a;
    public SubClassSS(){System.out.println("init SubClass");}
}