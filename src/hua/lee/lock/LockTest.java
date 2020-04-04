package hua.lee.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    private Lock lock = new ReentrantLock();

    public void doWork() {
        String name = Thread.currentThread().getName();

        try {
            System.out.println(name + ":start get lock");
            //lock.lock();
            lock.lockInterruptibly();
            System.out.println(name + ":already get lock");
            for (int i = 0; i < 6; i++) {
                Thread.sleep(1000);
                System.out.println(name + ":working num "+ i);
            }
        } catch (InterruptedException e) {
            System.out.println(name + ":Interrupt");
        }finally{
            try {
                lock.unlock();
                System.out.println(name + ": release unlock");
            } catch (Exception e) {
                System.out.println(name + ": unlock failed");
                System.out.println(name + ": failed desc:" + e.getMessage());
            }

        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        LockTest lockTest = new LockTest();
        Thread t0 = new Thread(new Runnable(){
        
            @Override
            public void run() {
                lockTest.doWork();
            }
        });
        Thread t1 = new Thread(new Runnable(){
        
            @Override
            public void run() {
                lockTest.doWork();
            }
        });
        // 启动线程t1
        t0.start();
        Thread.sleep(10);
        // 启动线程t2
        t1.start();
        Thread.sleep(100);
        // 线程t1没有得到锁，中断t1的等待
        t1.interrupt();
    }
}