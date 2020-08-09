package hua.lee.puzzle;

import java.util.Arrays;

public class RevertArray {
    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,53};

        int tailPos = array.length-1;
        int temp;
        for (int i = 0; i < array.length; i++) {
            if (i <= tailPos){
                int preVal = array[i];
                temp = array[tailPos];
                array[tailPos] = preVal;
                array[i] = temp;
                tailPos--;
            }else {
                break;
            }
        }
        System.out.println(Arrays.toString(array));
    }
}
