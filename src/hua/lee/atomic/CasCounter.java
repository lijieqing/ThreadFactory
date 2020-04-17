package hua.lee.atomic;

import java.util.concurrent.CountDownLatch;

public class CasCounter {
    /**
     * 原子操作，线程安全
     */
    private final SimulatedCAS simulatedCAS;
    /**
     * 非线程安全变量
     */
    private int temp;

    public CasCounter() {
        this.simulatedCAS = new SimulatedCAS();
    }

    public int get() {
        return simulatedCAS.get();
    }

    public int increment() {
        int value;
        do {
            value = simulatedCAS.get();
        } while (value != simulatedCAS.compareAndSwap(value, value + 1));
        return value + 1;
    }

    public void tempIncrement() {
        temp++;
    }

    public static void main(String[] args) throws InterruptedException {
        CasCounter casCounter = new CasCounter();
        CountDownLatch count = new CountDownLatch(50);
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 30; j++) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        casCounter.increment();

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        casCounter.tempIncrement();
                    }
                    count.countDown();
                }
            }).start();
        }
        count.await();
        System.out.println("Thread safe final cas Counter : " + casCounter.get());
        System.out.println("Thread unsafe final temp value : " + casCounter.temp);
    }
}
