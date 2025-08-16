package com.lxy.datastructure.collection.stack;

import com.lxy.datastructure.collection.exception.CollectionEmptyException;
import com.lxy.datastructure.collection.exception.CollectionFullException;
import com.lxy.datastructure.expression.Stack;
import com.lxy.datastructure.util.Constants;

public class SequenceStack implements Stack {

    private final int[] array;
    private int top;

    public SequenceStack() {
        array = new int[Constants.COLLECTION_CAPACITY];
        top = 0;
    }

    @Override
    public int size() {
        return top;
    }

    @Override
    public void push(int value) {
        if (top + 1 > array.length) {
            CollectionFullException.throwIt("Stack");
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
        if (isEmpty()) {
            CollectionEmptyException.throwIt("Stack");
        }
        return array[top - 1];
    }
}
