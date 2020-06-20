package hua.lee.jvm;

public class MethodTest {
    public static void main(String[] args) {
        Lava lava = new Lava();
        lava.flow();
    }

}

class Lava {
    private int speed = 5;
    
    public Lava() {
        System.out.println("lava constructor speed" + speed);
    }

    void flow() {
        System.out.println("lava after init speed" + speed);
    }
}