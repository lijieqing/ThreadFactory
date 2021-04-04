package hua.lee.thread;

import java.util.Arrays;

public class VolatileTest {
    static {
        System.out.println("VT static");
    }
    final A a = new A();
    private int w = 3;

    public VolatileTest() {
        System.out.println("VT");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(4^3);
        System.out.println(4|3);
        System.out.println(4&3);
    }
}

class A {
    public A() {
        System.out.println("A init");
    }
}

class Insert {
    static int seq(int[] arrays) {
        for (int i = 1; i < arrays.length; i++) {
            while (i > 0 && arrays[i] < arrays[i - 1]) {
                int temp = arrays[i];
                arrays[i] = arrays[i - 1];
                arrays[i - 1] = temp;
                i--;
            }
        }
        System.out.println(Arrays.toString(arrays));
        return 0;
    }
}
