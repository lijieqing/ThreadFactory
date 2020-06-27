package hua.lee.jvm;

public class InitExample {
}

class ExampleA {
    public static int random() {
        System.out.println("A random called");
        return (int) (Math.random() * 10);
    }
}

class ExampleB {
    static final int angle = 10;
    static final int length = angle * 2;
}

class ExampleC {
    static int a = ExampleI.ketch;
    static int b = ExampleI.mus;
}

interface ExampleI {
    int ketch = 9;
    int mus = (int) (Math.random() * 10);
}

class ExampleD {
    public static void main(String[] args) {
        String a = "ABC";
        String b = "A" + "B" + "C";
        String c = new String("ABC");
        StringBuilder sb = new StringBuilder();
        sb.append("A").append("B").append("C");
    }
}
class ExampleE{
    public static void main(String[] args) {
        String a = "ABC";
    }
}

class ExampleF{
    public static void main(String[] args) {
        String argsZero = args[0];
        String literalString = "hello";
        String internZero = argsZero.intern();
        if (argsZero==literalString){
            System.out.println("args[0] 和 literalString 是同一个对象");
        }else {
            System.out.println("args[0] 和 literalString 不是同一个对象");
        }
        if (internZero==literalString){
            System.out.println("internZero 和 literalString 是同一个对象");
        }else {
            System.out.println("internZero 和 literalString 不是同一个对象");
        }
    }
}