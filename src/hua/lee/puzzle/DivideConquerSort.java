package hua.lee.puzzle;

import java.util.Arrays;

public class DivideConquerSort {
    public static void main(String[] args) {
        int[] array = new int[]{5, 3,8,7,3, 7, 4, 0, 9, 1, 9, 8, 9};
        int start = 0;
        int end = array.length - 1;
        sort(array, start, end);
        System.out.println(Arrays.toString(array));
    }

    private static void sort(int[] array, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            sort(array, start, mid);
            sort(array, mid + 1, end);
            merge(array, start, mid, end);
        }
    }

    private static void merge(int[] array, int start, int mid, int end) {
        int[] left = new int[mid - start + 1
                + 1];
        int[] right = new int[end - (mid + 1) + 1
                + 1];
        System.arraycopy(array, start, left, 0, left.length - 1);
        System.arraycopy(array, mid + 1, right, 0, right.length - 1);
        right[right.length - 1] = Integer.MAX_VALUE;
        left[left.length - 1] = Integer.MAX_VALUE;

        int l = 0;
        int r = 0;
        for (int i = start; i <= end; i++) {
            if (left[l] == Integer.MAX_VALUE && right[r] == Integer.MAX_VALUE) {

            } else if (left[l] == Integer.MAX_VALUE && right[r] != Integer.MAX_VALUE) {
                array[i] = right[r];
                r++;
            } else if (right[r] == Integer.MAX_VALUE && left[l] != Integer.MAX_VALUE) {
                array[i] = left[l];
                l++;
            } else if (right[r] >= left[l]) {
                array[i] = left[l];
                l++;
            } else if (right[r] < left[l]) {
                array[i] = right[r];
                r++;
            }
        }

    }
}
