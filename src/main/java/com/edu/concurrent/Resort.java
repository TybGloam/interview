package com.edu.concurrent;

/**
 * Created by zhangxuan on 2019/3/27.
 */
public class Resort {

    public static int a = 0;

    public static int b = 0;

    public static int x = -1;

    public static int y = -1;

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                a=1;
                x=b;
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                b = 2;
                y = a;
            }
        }).start();

        Thread.sleep(4000);

        System.out.println(x);
        System.out.println(y);


    }


}
