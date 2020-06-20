package hua.lee.test;

public class PKUTest {
    public static void main(String[] args) {

    }

    private static int cutPizza(int t) {
        if (t == 0) {
            return 1;
        } else {
            return t + cutPizza(t - 1);
        }
    }
    private static void buyChicken(){
        boolean a = true;
    }
}
