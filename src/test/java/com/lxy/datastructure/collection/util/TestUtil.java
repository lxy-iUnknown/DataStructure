package com.lxy.datastructure.collection.util;

public class TestUtil {

    public static int element(int index) {
        return index * 2 + 1;
    }

    public static int newElement(int index) {
        return -index * 2 + 1;
    }

    public static int reverseElement(int element) {
        return (element - 1) / 2;
    }

    public static int reverseNewElement(int element) {
        return (1 - element) / 2;
    }
}
