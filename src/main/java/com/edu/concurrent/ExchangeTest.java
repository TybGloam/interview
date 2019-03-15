package com.edu.concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * exchange方法必须并发 当只有一个线程时,该方法阻塞
 * 也有超时api
 * 使用Timeout 超时后会抛出Timeoutexception
 * Created by zhangxuan on 2019/3/15.
 */
public class ExchangeTest implements Runnable {


    private Exchanger<String> exchanger;

    public ExchangeTest(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()+" 得到其他线程的值:"+exchanger.exchange(Thread.currentThread().getName(),1, TimeUnit.SECONDS));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExchangeTest et = new ExchangeTest(new Exchanger<String>());

        Thread t = new Thread(et);
        Thread t2 = new Thread(et);
        Thread t3 = new Thread(et);

        t.start();
        t2.start();
        t3.start();
    }
}
