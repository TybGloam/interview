package com.edu.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhangxuan on 2019/3/14.
 */
public class PrintABC implements Runnable {

    private Lock lock;

    private Condition condition;

    private Condition nextCondition;

    private String print;

    public PrintABC(Lock lock, Condition condition, Condition nextCondition, String print) {
        this.lock = lock;
        this.condition = condition;
        this.nextCondition = nextCondition;
        this.print = print;
    }

    @Override
    public void run() {

        lock.lock();

        try {
            for (int i = 0; i < 20; i++) {
                System.out.println(print);
                nextCondition.signal();

                if (i < 19){
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock(false);

        Condition ACondition = lock.newCondition();
        Condition BCondition = lock.newCondition();
        Condition CCondition = lock.newCondition();

        PrintABC a = new PrintABC(lock,ACondition,BCondition,"A");
        PrintABC b = new PrintABC(lock,BCondition,CCondition,"B");
        PrintABC c = new PrintABC(lock,CCondition,ACondition,"C");

        Thread at = new Thread(a);

        Thread bt = new Thread(b);

        Thread ct = new Thread(c);
        at.start();

        ct.start();
        bt.start();


    }
}
