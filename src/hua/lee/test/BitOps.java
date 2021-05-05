package hua.lee.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;

public class BitOps {
    public static void main(String[] args) {
        System.out.println(bitOpsRight2(-10));
        System.out.println(bitOpsRight3(-10));
        System.out.println(divide(-10));
    }

    public static int bitOpsRight2(int origin) {
        return origin >> 1;
    }

    public static int bitOpsRight3(int origin) {
        return origin >>> 1;
    }

    public static int divide(int origin) {
        return origin / 2;
    }
}
