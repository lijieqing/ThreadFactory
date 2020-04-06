package hua.lee.custom;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 用ReentrantLock实现Semaphore
 */
public class SemaphoreOnLock{
    private final Lock lock = new ReentrantLock();
    private final Condition permitsAvailable = lock.newCondition();
    private int permits;
    public SemaphoreOnLock(int initialPermits){
        lock.lock();
        try{
            permits = initialPermits;
        }finally{
            lock.unlock();
        }
    }

    public void acquire() throws InterruptedException {
        lock.lock();
        try{
            while(permits < 0){
                permitsAvailable.await();
            }
            --permits;
        }finally{
            lock.unlock();
        }
    }

    public void release(){
        lock.lock();
        try{
            ++permits;
            permitsAvailable.signal();
        }finally{
            lock.unlock();
        }
    }
}