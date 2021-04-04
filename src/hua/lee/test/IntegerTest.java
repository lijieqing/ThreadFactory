package hua.lee.test;

public class IntegerTest {
    private Integer a = 100;
    private Integer b = 100;
    private Integer c = 200;
    private Integer d = 200;
    public static void main(String[] args) {
        new IntegerTest().test();
    }
    public void test(){
        System.out.println(a==b);
        System.out.println(c==d);
    }
}
