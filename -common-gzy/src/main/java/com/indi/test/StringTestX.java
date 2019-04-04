package com.indi.test;

/**
 * Created by Administrator on 2019/3/11.
 */
public class StringTestX {
    public static void main(String[] args) {
        String a = "a";
        String b = "b";
        String c = "a" + b;
        String d = a + b;
        String e = "a" + "b";
        String f = new String("ab");
        String r = "ab";
        System.out.println(r == f);
        System.out.println(r.equals(d));
        System.out.println(r == e);
    }
}
