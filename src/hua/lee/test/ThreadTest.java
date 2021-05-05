package hua.lee.test;

import java.util.HashMap;
import java.util.concurrent.*;

public class ThreadTest {
    private static volatile boolean a = false;

    public static void main(String[] args) throws InterruptedException {
        parseInt("110086");
    }

    private static void parseInt(String s) {
        int res = 0;
        int[] carry = new int[]{
                1, 10, 100, 1000, 10000, 100000, 1000000
        };
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                int v = c - '0';
                res = res + v * carry[s.length() - 1 - i];
            } else {
                res = -1;
                break;
            }
        }
        System.out.println(res);
    }

    private static void test00() {
        int b = 100;
        int c = 101;
        int d = b + c;
        int e = 99;
    }

    private static void test01() {
        int b = 100;
        int c = 101;
        a = true;
        int d = b + c;
        int e = 99;
    }
}

class TestRun implements Runnable {
    int index;

    public TestRun(int index) {
        this.index = index;
    }

    @Override
    public void run() {
        System.out.println("Thread-Num-" + index + "-running");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread-Num-" + index + "-finish");
    }
}
