package com.edu.concurrent;

/**
 * 死锁案例:
 * 当两个线程均为拿一个锁的过程中去拿另一个锁时
 * 两个线程分别持有对方的第二个锁发生死锁
 */
public class DeadLock {

    private String a = "a";

    private String b = "b";

    /**
     * 避免死锁的方法:
     * 1.不拿多个锁
     * 2.避免一个线程在锁内占多个资源
     * 3.定时锁 lock.tryLock(timeout)
     */
    private void deadlock(){
        Thread t = new Thread(new Runnable() {
            public void run() {
                synchronized (a){
                    System.out.println(Thread.currentThread().getName()+":lock a");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("睡眠异常");
                    }
                    synchronized (b){
                        System.out.println(Thread.currentThread().getName()+":lock b");
                        System.out.println("b锁加持");
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                synchronized (b){
                    System.out.println(Thread.currentThread().getName()+":lock b");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("睡眠异常");
                    }
                    synchronized (a){
                        System.out.println(Thread.currentThread().getName()+":lock a");
                        System.out.println("a锁加持");
                    }
                }
            }
        });

        t.start();
        t2.start();
    }

    public static void main(String[] args) {
        new DeadLock().deadlock();
    }
}
