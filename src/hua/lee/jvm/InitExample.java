package hua.lee.jvm;

public class InitExample {
}

class ExampleA{
    public static int random(){
        System.out.println("A random called");
        return (int) (Math.random()*10);
    }
}
class ExampleB{
    static final int angle = 10;
    static final int length = angle * 2;
}

class ExampleC{
    static int a = ExampleI.ketch;
    static int b = ExampleI.mus;
}
interface ExampleI{
    int ketch = 9;
    int mus = (int) (Math.random()*10);
}