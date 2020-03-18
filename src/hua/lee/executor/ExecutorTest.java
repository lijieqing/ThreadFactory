package hua.lee.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import hua.lee.thread.Logger;
/**
 * https://webvpn.bjmu.edu.cn/users/sign_in
 */
public class ExecutorTest {
    public static void main(String[] args) {
        TimingThreadPool pool = new TimingThreadPool(10, 11, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
        pool.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("start Executor");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end Executor");
                pool.terminated();
            }
        });
    }
}

class TimingThreadPool extends ThreadPoolExecutor {
    private final ThreadLocal<Long> startTime = new ThreadLocal<>();
    private final Logger log = Logger.getLogger();
    private final AtomicLong numberTasks = new AtomicLong();
    private final AtomicLong totalTime = new AtomicLong();

    public TimingThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
            BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        log.fine(String.format("beforeExecute Thread %s: start %s", t, r));
        startTime.set(System.nanoTime());
        super.beforeExecute(t, r);
    }
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        try {
            long endTime = System.nanoTime();
            long taskTime = endTime - startTime.get();
            numberTasks.incrementAndGet();
            totalTime.addAndGet(taskTime);
            log.fine(String.format("afterExecute Thread %s: end %s: time=%dns", t, r,taskTime));
        } finally {
            super.afterExecute(r, t);
        }
    }

    @Override
    protected void terminated() {
        try {
            log.fine(String.format("Terminated: avg time=%dns", totalTime.get()/numberTasks.get()));
        } finally {
            super.terminated();
        }
    }
    
}