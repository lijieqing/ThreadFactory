package hua.lee.test;

import java.util.concurrent.locks.ReentrantLock;

public class InnerClassTest {
    private int outA = 10;
    class InnerA{
        void testInner(){
            outA++;
        }
    }
    final void testF(){

    }
    void testF(String a){

    }

    public static void main(String[] args) {
        System.out.println("test");
    }
}

class T extends InnerClassTest{
    @Override
    void testF(String a) {
        super.testF(a);
    }
}
