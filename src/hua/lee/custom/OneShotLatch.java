package hua.lee.custom;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class OneShotLatch {
    private final Sync sync = new Sync();

    public void signal() {
        sync.releaseShared(0);
    }

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(0);
    }

    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected int tryAcquireShared(int arg) {
            int state = getState();
            System.out.println("state = " + state);
            return getState() == 1 ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }
    }

    public static void main(String[] args) {
        OneShotLatch osl = new OneShotLatch();
        new Thread(() -> {
            System.out.println("we are in main 01 thread, and start osl await");
            try {
                osl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main 01 thread osl await finished");
        }).start();
        new Thread(() -> {
            System.out.println("we are in main 02 thread, and start osl await");
            try {
                osl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main 02 thread osl await finished");
        }).start();
        new Thread(() -> {

            System.out.println("we are in main 03 thread, and first sleep 5s");
            try {
                Thread.sleep(5000);
                System.out.println("we are in main 03 thread, and start osl await");
                osl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main 03 thread osl await finished");
        }).start();
        new Thread(() -> {
            System.out.println("we are in work thread,and we start waiting");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("work finish,now we signal main thread");
            osl.signal();
        }).start();
    }
}