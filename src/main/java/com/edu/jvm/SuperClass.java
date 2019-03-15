package com.edu.jvm;

/**
 * 通过子类引用父类的静态变量,不会导致子类初始化
 * Created by zhangxuan on 2019/3/15.
 */
public class SuperClass {

    static{
        System.out.println("SuperClass init");
    }

    public static int val = 123;

    public static final Integer i1 = new Integer("233");

    public static final String s1 = "魑魅魍魉";

}

