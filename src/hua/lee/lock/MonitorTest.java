package hua.lee.lock;

public class MonitorTest {
    public static void main(String[] args) {
        byte[] buffer = new byte[4];
        MonitorObj monitorObj = new MonitorObj();

        Thread read00 = new Thread() {
            @Override
            public void run() {
                System.out.println("read00 准备获取锁");
                synchronized (monitorObj) {
                    System.out.println("read00 = " + buffer[3]);
                    try {
                        Thread.sleep(1000);

                        monitorObj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("read00 = " + buffer[3]);
                }
                System.out.println("read00 释放锁");
            }
        };
        Thread read01 = new Thread() {
            @Override
            public void run() {
                System.out.println("read01 准备获取锁");
                synchronized (monitorObj) {
                    System.out.println("read01 = " + buffer[3]);
                    try {
                        Thread.sleep(1000);

                        monitorObj.wait(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("read01 = " + buffer[3]);
                }
                System.out.println("read01 释放锁");
            }
        };

        Thread write = new Thread() {
            @Override
            public void run() {
                System.out.println("write 准备获取锁");
                synchronized (monitorObj) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    buffer[3] = 99;
                    //monitorObj.notifyAll();
                    try {
                        Thread.sleep(3000);
                        System.out.println("write thread finish");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        read00.start();
        read01.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        write.start();

    }
}

class MonitorObj {
}

