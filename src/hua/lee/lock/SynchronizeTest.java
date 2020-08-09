package hua.lee.lock;

import java.util.Arrays;

public class SynchronizeTest {
    private int[] array = new int[]{1, 2, 3, 4};

    public void expandArray() {
        synchronized (this){
            for (int i = 0; i < array.length; i++) {
                array[i] = array[i] / 0;
            }
            System.out.println(Arrays.toString(array));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizeTest st = new SynchronizeTest();
        //st.expandArray();
        st.wait();

    }

}
