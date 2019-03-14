package com.edu.concurrent;

/**
 * 只有在运行中的线程可以被打断.
 */
public class Interrupted  {

    public static void main(String[] args) throws InterruptedException {
        A a = new A();
        Thread t = new Thread(a);
        t.setDaemon(true);
        B b = new B();
        Thread t2 = new Thread(b);
        t2.setDaemon(true);
        t.start();
        t2.start();
        Thread.sleep(5000);

        t.interrupt();
        t2.interrupt();
        System.out.println("a is :"+t.isInterrupted());
        System.out.println("b is :"+t2.isInterrupted());
        Thread.sleep(2000);
    }


    static class A implements Runnable{

        public void run() {
            try {
                while (true){
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
            }
        }
    }

    static class B implements Runnable{

        public void run() {
            while (true){

            }
        }
    }
}
