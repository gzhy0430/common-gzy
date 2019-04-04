package com.indi.design.proto;

import java.io.*;
import java.util.Date;

/**
 * Created by Administrator on 2019/3/25.
 * 原型模式--运用原型模式重构一段业务代码。
 */
public class ProtoTypeTestX {
    public static void main(String[] args) {
        ReferenceType rt = new ReferenceType(1, 2);
        ProtoPojo p = new ProtoPojo();
        p.setA(123);
        p.setC(new Date());
        p.setRt(rt);
        ProtoPojo p1 = p.simpleclone();
        System.out.println(p == p1);
        System.out.println(p.getRt() == p1.getRt());
        System.out.println(p.getC() == p1.getC());
        ProtoPojo p2 = p.clone();
        System.out.println(p == p2);
        System.out.println(p.getRt() == p2.getRt());
        System.out.println(p.getC() == p2.getC());
        System.out.println(p2.getRt());
    }
}

class ProtoPojo implements Serializable{
    private int a;
    private Date c;
    private ReferenceType rt;//transient 假如该引用类型没有实现Serializable 则该变量不能被实例化
    public int getA() {
        return a;
    }
    public void setA(int a) {
        this.a = a;
    }
    public Date getC() {
        return c;
    }
    public void setC(Date c) {
        this.c = c;
    }
    public ReferenceType getRt() {
        return rt;
    }
    public void setRt(ReferenceType rt) {
        this.rt = rt;
    }
    public ProtoPojo simpleclone(){
        ProtoPojo obj = new ProtoPojo();
        obj.setA(this.getA());
        obj.setC(this.getC());
        obj.setRt(this.getRt());
        return obj;
    }
    private ProtoPojo deepclone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);
        byte[] bytes = baos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (ProtoPojo) ois.readObject();
    }
    @Override
    protected ProtoPojo clone() {
        try {
            return this.deepclone();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

class ReferenceType implements Serializable{
    private int x;
    private int y;
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("{");
        sb.append("x=").append(x);
        sb.append(", y=").append(y);
        sb.append('}');
        return sb.toString();
    }
    public ReferenceType(int x, int y) {
        this.x = x;
        this.y = y;
    }
}