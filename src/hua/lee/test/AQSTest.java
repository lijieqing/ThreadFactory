package hua.lee.test;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AQSTest {
    private ReentrantLock reentrantLock;
    private FutureTask<String> futureTask;
    private Semaphore semaphore;
    private CountDownLatch countDownLatch;
    private ReentrantReadWriteLock reentrantReadWriteLock;

    private static class MyCallaback implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("=========1=========");
            Thread.sleep(5000);
            System.out.println("=========2=========");
            System.out.println("=========3=========");
            return "finish";
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(11);
        Future<String> task = executor.submit(new MyCallaback());
        String res;
        try {
            res = task.get(2, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            task.cancel(true);
            res = "false";
        }
        System.out.println("first task res = "+res);
        Thread.sleep(1000);
        task = executor.submit(new MyCallaback());
        System.out.println("second task res = "+task.get());
    }

}
