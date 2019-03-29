package com.edu.concurrent;

/**
 * synchronized与volatile的对比
 *
 * volatile不保证线程安全,只能保证多线程的可见性
 * 所以volatile只可以使用get方法来保证读取最新的值
 * 对值进行操作的行为依然需要使用syn
 *
 * synchronized:
 * 每一个对象都可以作为锁
 * 同步方法锁当前实例
 * static同步方法,锁的是当前类的Class对象
 * 同步代码块锁的是Syn括号内的对象
 *
 * Java对象头里记录锁的状态(是否有锁),对象分代年龄,是否偏向锁1bits,锁标记位2bits
 * 锁有四种状态:无锁状态-->偏向锁-->轻量级-->重量级
 * 默认偏向锁是开启状态
 * 轻量级锁如果获取不到会膨胀为重量级锁
 */
public class SynAndVol implements Runnable {

    private int i1 = 0;
    private volatile int i2 = 0;

    public void run() {
//        System.out.println(Thread.currentThread().getName());
        i1++;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i2++;

    }

    public void get(){
        System.out.println(i1);

        System.out.println(i2);
    }

    public static void main(String[] args) throws InterruptedException {
        SynAndVol synAndVol = new SynAndVol();
        for (int i = 0; i < 10000; i++) {
            Thread t = new Thread(synAndVol);
            t.start();

        }

    }
}
