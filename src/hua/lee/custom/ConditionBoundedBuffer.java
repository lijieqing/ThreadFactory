package hua.lee.custom;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionBoundedBuffer<T> {
    protected final Lock lock = new ReentrantLock();
    /**
     * 集合元素未填满
     */
    private final Condition notFull = lock.newCondition();
    /**
     * 集合元素未空
     */
    private final Condition notEmpty = lock.newCondition();
    private final T[] items = (T[]) new Object[100];
    private int tail, head, count;

    public void put(T t) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                System.out.println("集合元素已满，等待被取出通知");
                notFull.await();
            }
            items[tail] = t;
            if (++tail == items.length) {
                tail = 0;
            }
            ++count;
            System.out.println("集合放入新元素，发出非空通知");
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            T t = items[head];
            if (++head == items.length) {
                head = 0;
            }
            --count;
            notFull.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionBoundedBuffer<String> cb = new ConditionBoundedBuffer<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String s = cb.take();
                    System.out.println("Thread 0 take data = " + s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    String s = "222222";
                    System.out.println("Thread 1 put data = " + s);
                    cb.put(s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}