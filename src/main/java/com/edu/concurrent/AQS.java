package com.edu.concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;

/**
 * Created by zhangxuan on 2019/3/14.
 */
public class AQS {

    private static class Sync extends AbstractQueuedSynchronizer{
        /**
         * 是否处于占用状态
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            System.out.println("判断是否占用");
            return getState() == 1;
        }

        /**
         * 当状态为0的时候获取锁
         * @param arg
         * @return
         */
        @Override
        protected boolean tryAcquire(int arg) {
            System.out.println("尝试获取锁");
            if (compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;

        }

        /**
         * 释放锁并将状态 重置为0
         * @param arg
         * @return
         */
        @Override
        protected boolean tryReleaseShared(int arg) {
            System.out.println("释放锁");
            if (getState() == 0){
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        /**
         * 返回一个condition
         * @return
         */
        Condition newCondition (){
            System.out.println("新建condition");
            return new ConditionObject();
        }
    }

    private final Sync sync = new Sync();

    public void lock(){
        sync.acquire(1);
    }

    public boolean tryLock(){
        return sync.tryAcquire(1);
    }

    public void unLock(){

    }
}
