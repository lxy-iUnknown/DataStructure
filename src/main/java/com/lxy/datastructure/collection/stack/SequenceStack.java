package com.lxy.datastructure.collection.stack;

import com.lxy.datastructure.collection.common.CollectionEmptyException;
import com.lxy.datastructure.collection.common.CollectionFullException;
import com.lxy.datastructure.util.Constants;

public class SequenceStack implements Stack {

    private final int[] array;
    private int top;

    public SequenceStack() {
        array = new int[Constants.COLLECTION_CAPACITY];
        top = 0;
    }

    private void ensureNotEmpty() {
        if (top <= 0) {
            throw new CollectionEmptyException("Stack is empty");
        }
    }

    @Override
    public int size() {
        return top;
    }

    @Override
    public void push(int value) {
        if (top + 1 > array.length) {
            throw new CollectionFullException("Stack is full");
        }
        array[top++] = value;
    }

    @Override
    public int pop() {
        int value = peek();
        top--;
        return value;
    }

    @Override
    public int peek() {
        ensureNotEmpty();
        return array[top - 1];
    }
}
