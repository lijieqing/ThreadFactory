package hua.lee.jvm;

public interface Angry {
    String greeting = "Grrrr!";
    int angerLevel = Dog.getAngerLevel();
}
class Dog{
    static final String greeting = "woof woof world";
    static{
        System.out.println("Dog was initialized");
    }
    static int getAngerLevel(){
        System.out.println("Angry was initialized");
        return 1;
    }
}

class Example{
    public static void main(String[] args) {
        if (args.length != 2){
            System.out.println("illegal args");
            return;
        }
        //args[0] + args[1]
        //编译器会创建StringBuilder
        //通过 append 连接
        //并通过 toString 转成 String 对象
        System.out.println(args[0] + args[1]);
    }
    static{
        System.out.println("Example was initialized");
    }
}

class ExampleCons extends Example{
    private int width = 3;

    public ExampleCons() {
        this(1);
        System.out.println("ExampleCons(),width = " + width);
    }

    public ExampleCons(int width) {
        this.width = width;
        System.out.println("ExampleCons(int),width = " + width);
    }
    public ExampleCons(String msg) {
        super();
        System.out.println("ExampleCons(String),width = " + width);
        System.out.println(msg);
    }

    public static void main(String[] args) {
        String msg = "Test Constructor MSG";
        ExampleCons one = new ExampleCons();
        ExampleCons two = new ExampleCons(2);
        ExampleCons three = new ExampleCons(msg);
    }
}

class MyThread extends Thread implements Cloneable{
}

class StaticTest{
    static int len = 9;
}