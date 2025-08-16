package com.lxy.datastructure.sort;

public class SelectionSort {
    public static void sort(int[] array) {
        int length = array.length;
        for (var i = 0; i < length - 1; i++) {
            int min = Integer.MAX_VALUE;
            int index = 0;
            for (var j = i; j < length; j++) {
                int value = array[j];
                if (value < min) {
                    min = value;
                    index = j;
                }
            }
            SortUtil.swap(array, i, index);
        }
    }
}
