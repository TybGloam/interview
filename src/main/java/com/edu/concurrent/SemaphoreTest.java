package com.edu.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore 向量 可以限制方法的最大并发数
 * Created by zhangxuan on 2019/3/15.
 */
public class SemaphoreTest {
    /**
     * 构造参数中的数表示同一时间 acquire和release之间可以执行最大线程数
     * 第二个参数为是否公平
     */
    private Semaphore semaphore = new Semaphore(10,true);

    public void testMethod() {
        try {
            semaphore.acquire(2);
            System.out.println(Thread.currentThread().getName() + "begin:" + System.currentTimeMillis());

            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + " end " + System.currentTimeMillis() + "waiting thread count"+semaphore.getQueueLength());
            System.out.println("waiting semaphore count"+semaphore.hasQueuedThreads());
            semaphore.release(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);

        SemaphoreTest st = new SemaphoreTest();

        for (int i = 0; i < 10; i++) {
            es.execute(new Runnable() {
                @Override
                public void run() {
                    st.testMethod();
                }
            });

        }
        es.shutdown();
    }
}
