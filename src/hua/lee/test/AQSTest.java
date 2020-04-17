package hua.lee.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AQSTest {
    private ReentrantLock reentrantLock;
    private FutureTask<String> futureTask;
    private Semaphore semaphore;
    private CountDownLatch countDownLatch;
    private ReentrantReadWriteLock reentrantReadWriteLock;

}
