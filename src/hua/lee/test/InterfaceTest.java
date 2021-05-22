package hua.lee.test;

public interface InterfaceTest {
    public static final int Data = 100;
    default void getString(){
        System.out.println("hello");
    }
    void print();
    static void get(){
        System.out.println("static");
    }
}
class B{
    public void getString(){
        System.out.println("B");
    }
}
abstract class AbsTest{
    static {

    }
    abstract void print();
    static void get(){
        System.out.println("static");
    }
}

class  A extends B implements InterfaceTest {
    public static void main(String[] args) {
        A a = new A();
        a.getString();
    }

    @Override
    public void print() {

    }
}
