package com.lxy.datastructure.sort;

public class SortUtil {
    public static void swap(int[] array, int i, int j) {
        var temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
