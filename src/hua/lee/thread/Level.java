package hua.lee.thread;

public final class Level {
    private Level() {
    }

    public static final int SERVER = 0;
    public static final int FINE = 1;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                while (a < 8) {
                    System.out.println("A before yield " +  a);
                    Thread.yield();
                    System.out.println("A after yield " + a);
                    a++;
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("B before yield");
                Thread.yield();
                System.out.println("B after yield");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("B after sleep");
            }
        }).start();
    }
}