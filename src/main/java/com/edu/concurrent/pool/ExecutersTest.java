package com.edu.concurrent.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangxuan on 2019/3/15.
 */
public class ExecutersTest {

    public static void main(String[] args) {
        //0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>()
        ExecutorService es = Executors.newCachedThreadPool();


    }
}
