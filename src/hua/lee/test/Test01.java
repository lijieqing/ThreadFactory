package hua.lee.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Test01 {
    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        a.add("A");
        a.add("B");
        a.add("C");
        a.add("C");
        a.add("B");
        a.add("D");
        a.add("A");
        t(a);
    }

    public static void t2() {
        // task 00
        LinkedList<String> task00 = new LinkedList<>();

        // task 01
        LinkedList<String> task01 = new LinkedList<>();

        String s;
        while ((s=task00.pop())!=null){
            String temp = task01.peek();
            if (s.equals(temp)){
                task01.pop();
            }else {
                task01.push(s);
            }
        }
    }

    public static void t(List<String> list){
        LinkedList<String> task00 = new LinkedList<>();
        String temp;
        for (String s : list) {
            if ((temp=task00.peek())!=null){
                if (s.equals(temp)){
                    task00.pop();
                }else {
                    task00.push(s);
                }
            }else {
                task00.push(s);
            }
        }
        System.out.println(Arrays.toString(task00.toArray()));
    }
}
