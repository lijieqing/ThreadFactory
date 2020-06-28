package hua.lee.jvm;

public interface Friendly {
    void sayHello();
    void sayGoodbye();
}

class Dog{
    private final int wagCount = (int) (Math.random() * 5 + 1);

    void sayHello() {
        System.out.print("wag");
        for (int i = 0; i < wagCount; i++) {
            System.out.print(", wag");
        }
        System.out.println(".");
    }

    @Override
    public String toString() {
        return "woof!";
    }
}

class CockerSpaniel extends Dog implements Friendly {

    private final int woofCount = (int) (Math.random() * 4 + 1);
    private final int wimperCount = (int) (Math.random() * 3 + 1);
    @Override
    public void sayHello() {
        super.sayHello();
        System.out.print("woof");
        for (int i = 0; i < woofCount; i++) {
            System.out.print(", woof");
        }
        System.out.println(".");
    }

    @Override
    public void sayGoodbye() {
        System.out.print("wimper");
        for (int i = 0; i < wimperCount; i++) {
            System.out.print(", wimper");
        }
        System.out.println(".");
    }
}

class Cat implements Friendly{
    public void eat(){
        System.out.println("Chomp, chomp, chomp.");
    }

    @Override
    public void sayHello() {
        System.out.println("Rub, rub ,rub.");
    }

    @Override
    public void sayGoodbye() {
        System.out.println("Scamper.");
    }
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Meow!");
    }
}

class Test{
    public static void main(String[] args) {
        Dog d = new Dog();
        Cat c = new Cat();
        CockerSpaniel cs = new CockerSpaniel();
        d.sayHello();
        c.sayHello();
        cs.sayHello();
    }
}