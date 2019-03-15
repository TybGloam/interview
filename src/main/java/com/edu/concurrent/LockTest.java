package com.edu.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  Lock与syn不同,Lock需要显式的获取和释放锁
 *
 *
 * Created by zhangxuan on 2019/3/14.
 */
public class LockTest {

    Lock lock = new ReentrantLock();
    /**
     * 公平锁 按照等待队列的顺序去拿锁
     */
    Lock fairLock = new ReentrantLock(true);

    public void fair(){
        fairLock.lock();
        System.out.println(Thread.currentThread().getName()+"lock");
        fairLock.unlock();
    }



    public void getA() throws InterruptedException {
        lock.lock();
        System.out.println("a");
        Thread.sleep(1000);
        System.out.println("a sleep end");
        getB();
        lock.unlock();
        return ;
    }

    public void getB() throws InterruptedException {
        lock.lock();
        System.out.println("b");
        Thread.sleep(500);
        System.out.println("b sleep end");
        lock.unlock();
    }

    public void getC() throws InterruptedException {
        boolean b = lock.tryLock();
        System.out.println("C尝试获取到锁结果:"+b);

        boolean b1 = lock.tryLock(2, TimeUnit.SECONDS);
        System.out.println("C定时尝试获取到锁结果"+b1);
    }

    public static void main(String[] args) throws InterruptedException {
        LockTest lockTest = new LockTest();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lockTest.getA();
                } catch (InterruptedException e) {
                }
            }
        });


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lockTest.getC();
                } catch (InterruptedException e) {
                }
            }
        });
        t1.start();
        t2.start();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Runnable fair = new Runnable() {
            @Override
            public void run() {
                lockTest.fair();
            }
        };
        System.out.println("++++++++++++++ fair begin +++++++++++++++");
        for (int i = 0; i < 100; i++) {
            executorService.execute(fair);

        }

        executorService.shutdown();

    }
}
