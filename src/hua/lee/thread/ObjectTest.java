package hua.lee.thread;

public class ObjectTest {
    final static Object lock = new Object();
    static boolean finish = false;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("test start");
        new Thread() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        Thread.sleep(5000);
                        System.out.println("5s down,notify cancel wait");
                        finish = true;
                        lock.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        synchronized (lock) {
            System.out.println("get lock");
            while (!finish) {
                System.out.println("finish is false!");
                lock.wait();
            }
        }
        System.out.println("wait finish");
    }
}
