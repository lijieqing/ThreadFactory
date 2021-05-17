package hua.lee.test;

import java.util.Scanner;

public class ForHonor00 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            System.out.println(inverse(in.nextLine()));
        }
    }
    private static String inverse(String old){
        StringBuilder res = new StringBuilder();
        if (old != null){
            int len = old.length();
            for (int i = len-1; i >=0; i--) {
                char val = old.charAt(i);
                res.append(val);
            }
        }
        return res.toString();
    }

}
