package com.edu.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用countdown模拟赛跑
 * Created by zhangxuan on 2019/3/15.
 */
public class CountDownTest implements Runnable {

    private CountDownLatch comingtag;
    private CountDownLatch waittag;
    private CountDownLatch waitRuntag;
    private CountDownLatch beginTag;
    private CountDownLatch endTag;

    public CountDownTest(CountDownLatch comingtag, CountDownLatch waittag, CountDownLatch waitRuntag, CountDownLatch beginTag, CountDownLatch endTag) {
        this.comingtag = comingtag;
        this.waittag = waittag;
        this.waitRuntag = waitRuntag;
        this.beginTag = beginTag;
        this.endTag = endTag;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + "马上抵达战场!");
            Thread.sleep((long) (Math.random()*10000));
            System.out.println(Thread.currentThread().getName()+"到达赛场!");
            comingtag.countDown();
            System.out.println(Thread.currentThread().getName()+"等待裁判发号!");
            waittag.await();
            Thread.sleep((long) (Math.random()*10000));
            waitRuntag.countDown();
            System.out.println(Thread.currentThread().getName()+"准备完成!");
            beginTag.await();
            Thread.sleep((long) (Math.random()*10000));

            endTag.countDown();
            System.out.println(Thread.currentThread().getName()+"到达终点");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch comingtag = new CountDownLatch(10);
        CountDownLatch waitingtag = new CountDownLatch(1);
        CountDownLatch waitRuntag = new CountDownLatch(10);
        CountDownLatch beginTag = new CountDownLatch(1);
        CountDownLatch endTag = new CountDownLatch(10);

        CountDownTest countDownTest = new CountDownTest(comingtag, waitingtag, waitRuntag, beginTag, endTag);

        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            es.execute(countDownTest);
        }
        //入场等待
        comingtag.await();
        System.out.println("所有人抵达战场");
        //进入赛场
        waitingtag.countDown();
        System.out.println("各就各位");
        //裁判等待
        waitRuntag.await();
        //开炮
        beginTag.countDown();
        System.out.println("bang!");

        endTag.await();
        System.out.println("比赛结束!");
        es.shutdown();

    }
}
