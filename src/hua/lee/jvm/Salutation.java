package hua.lee.jvm;

public class Salutation {
    private static final String hello = "Hello World!";
    private static final String greeting = "Greeting Planet!";
    private static final String salutation = "Salutation orbs!";
    private static int choice = (int) (Math.random()*5*2.99);

    public static void main(String[] args) {
        String s = hello;
        if (choice==1){
            s = greeting;
        }else if (choice==2){
            s=salutation;
        }
        System.out.println(s);
    }
}
