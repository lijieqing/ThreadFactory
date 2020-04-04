package hua.lee.lock;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DeadLock {
    private CountDownLatch latchA = new CountDownLatch(1);
    private CountDownLatch latchB = new CountDownLatch(1);
    /**
     * 资源死锁
     */
    class ResourceDeadLock{
        ExecutorService executors = Executors.newFixedThreadPool(1);
        Callable<Boolean> a = new Callable<Boolean>() {

            @Override
            public Boolean call() throws Exception {
                Callable<Boolean> b = new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {

                        return null;
                    }
                };
                Future<Boolean> res = executors.submit(b);
                return res.get();
            }
        };
        public void start() {
            executors.submit(a);
        }
    
    }
    /**
     * Thread A and Thread B will in a Lock-Ordering DeadLock.
     */
    class LockOrderingDeadLock{
        public void start(){
            new A().start();
            new B().start();
        }
    }
    class A extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                latchB.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latchA.countDown();
        }
    }
    class B extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                latchA.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latchB.countDown();
        }
    }
}