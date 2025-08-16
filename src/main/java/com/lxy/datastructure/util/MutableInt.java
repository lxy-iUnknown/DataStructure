package com.lxy.datastructure.util;

public class MutableInt {
    private int value;

    public MutableInt() {
        this(0);
    }

    public MutableInt(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void increment() {
        value++;
    }

    public int incrementAndGet() {
        return value++;
    }

    @Override
    public String toString() {
        return "MutableInt{" + "value=" + value + '}';
    }
}
