package com.edu.jvm;

/**
 * Created by zhangxuan on 2019/3/15.
 */
public class Notinit {

    public static void main(String[] args) {
        //通过子类取父类的属性 不会加载子类
//        System.out.println(SubClass.val);

        //初始化数组不会加载类 创建动作由new array触发
//        SuperClass[] ss = new SuperClass[10];

        //在编译期已经把字符串加载到了class文件中的常量池,可能是字符串不可变?
        System.out.println(SuperClass.s1);
    }
}
