package com.edu.concurrent.pool;

import java.util.concurrent.*;

/**
 * Created by zhangxuan on 2019/3/15.
 */
public class ExecutersTest implements Runnable {

    public static void main(String[] args) {

        /**
         * int corePoolSize,核心线程数
            int maximumPoolSize,最大线程数
         long keepAliveTime,线程存活时间
         TimeUnit unit,存活时间单位
         BlockingQueue<Runnable> workQueue, 任务队列类型
         ThreadFactory threadFactory,线程工厂
         RejectedExecutionHandler handler 拒绝策略
         */
//        Executors.newFixedThreadPool();
        ThreadPoolExecutor t =
                new ThreadPoolExecutor(2,3,5,TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(2));
        //可以看linkedBlockingQueue的构造器代码 可以指定队列长度,如果不指定 使用int的最大值
        ThreadPoolExecutor tt = new ThreadPoolExecutor(2,3,5,TimeUnit.SECONDS,new LinkedBlockingQueue<>());

        ThreadPoolExecutor ttt = new ThreadPoolExecutor(2,3,5,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(2));

        for (int i = 0; i < 6; i++) {
            t.execute(new ExecutersTest());
//            tt.execute(new ExecutersTest());
        }
        t.shutdown();
        System.out.println(t.isShutdown());
        tt.shutdown();
        System.out.println(tt.isShutdown());
        ttt.shutdown();
        System.out.println(ttt.isShutdown());
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" start: "+ System.currentTimeMillis());
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName()+ " end: "+System.currentTimeMillis());
        } catch (InterruptedException e) {

        }
    }
}
