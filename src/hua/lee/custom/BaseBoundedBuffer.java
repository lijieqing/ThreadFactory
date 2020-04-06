package hua.lee.custom;

/**
 * BaseBoundedBuffer,在这个类中实现了一个基于数组的循环缓存， 其中各个缓存状态变量(
 * buf、head、tail和count)均由缓存的内置锁来保护。 它还提供了同步的doPut和doTake方法，
 * 并在子类中通过这些方法来实现put和take操作，底层的状态将对子类隐藏。
 * 
 * @param <V> 缓存类型
 */
public class BaseBoundedBuffer<V> {
    private final V[] buf;
    private int tail;
    private int head;
    private int count;

    public BaseBoundedBuffer(int capacity) {
        buf = (V[]) new Object[capacity];
    }

    protected synchronized final void doPut(V v) {
        buf[tail] = v;
        if (++tail == buf.length) {
            tail = 0;
        }
        ++count;
    }

    protected synchronized final V doTake() {
        V v = buf[head];
        buf[head] = null;
        if (++head == buf.length) {
            head = 0;
        }
        --count;
        return v;
    }

    public synchronized final boolean isFull() {
        return count == buf.length;
    }

    public synchronized final boolean isEmpty() {
        return count == 0;
    }

    public synchronized final void waitTest(long ms) throws InterruptedException {
        wait(ms);
    }

    public synchronized final void notifyTest(){
        notifyAll();
    }
    public static void main(String[] args) {
        BaseBoundedBuffer<String> a = new BaseBoundedBuffer<>(1);
        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("01 thread start wait");
                try {
                    a.waitTest(15000);
                    System.out.println("01 thread wait finish");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("01 thread wait InterruptedException");
                }
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(3 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("02 thread start notify");
                a.notifyTest();
                System.out.println("02 thread notify finish");
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("03 thread start wait");
                try {
                    a.waitTest(14000);
                    System.out.println("03 thread wait finish");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("03 thread wait InterruptedException");
                }
            }
        }).start();
    }
}