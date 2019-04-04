package com.indi.argorithm;

/**
 * Created by Administrator on 2019/3/12.
 */
public class OperatorX {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(0));
//        按位与运算符&--同位为1则为1
//        与运算用途：清零，任何一个数&0  二进制低位取数运算 1010 1110 & 0000 1111 = 0000 1110
        System.out.println(3&5);

//        按位或运算|--只要有一个为1则为1
//        特殊作用  二进制低位置零 1010 1110 | 0000 1111 = 1010 1111
        System.out.println(3|5);


//        异或运算符^--同位相同则为0，同为不同则为1
//        特殊作用：二进制低位翻转，1010 1110 ^ 0000 1111 = 1010 0001,与0异或保留原值
        System.out.println(3^5);//6

//        取反运算符~--1变0,0变1
        System.out.println(~0);
        System.out.println(Integer.toBinaryString(~0));

//        左移右移运算符 << >>(正数左侧空位填1，负数左侧空位填1)

//        无符号右移运算符 >>> 左侧空位填0
    }
}
