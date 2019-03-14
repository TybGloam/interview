package com.edu.concurrent;

/**
 * 守护线程随着主用户线程的的结束而一起结束
 *
 */
public class Daemon implements Runnable {
    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            System.out.println("Daemon catch run");
        }finally {
            System.out.println("Daemon finally run");
        }
    }

    public static void main(String[] args) {
        Daemon daemon = new Daemon();
        Thread t = new Thread(daemon);
        t.setDaemon(true);
        t.setPriority(1);
        t.start();
    }
}
