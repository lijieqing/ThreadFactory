package hua.lee.puzzle;

public class Hanoi {
    public static void main(String[] args) {
        move(3,"A","C","B");
    }
    private static void move(int pcs, String start, String end, String buffer) {
        if (pcs == 1) {
            System.out.println("从" + start + "移动一片到" + end);
        } else if (pcs == 2) {
            System.out.println("从" + start + "移动一片到" + buffer);
            System.out.println("从" + start + "移动一片到" + end);
            System.out.println("从" + buffer + "移动一片到" + end);
        } else {
            move(pcs - 1, start, buffer, end);
            System.out.println("从" + start + "移动一片到" + end);
            move(pcs - 1, buffer, end, start);
        }
    }
}
