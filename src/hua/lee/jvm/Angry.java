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
        System.out.println(Angry.angerLevel);
        System.out.println(Dog.greeting);
    }
    static{
        System.out.println("Example was initialized");
    }
}