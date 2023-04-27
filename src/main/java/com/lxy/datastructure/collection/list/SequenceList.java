package com.lxy.datastructure.collection.list;

import com.lxy.datastructure.collection.common.CollectionEmptyException;
import com.lxy.datastructure.collection.common.CollectionFullException;
import com.lxy.datastructure.util.Constants;

import java.util.Objects;
import java.util.function.IntConsumer;

public class SequenceList implements List {

    private final int[] array;
    private int length;

    public SequenceList() {
        array = new int[Constants.COLLECTION_CAPACITY];
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public int get(int index) {
        Objects.checkIndex(index, length);
        return array[index];
    }

    @Override
    public void set(int index, int value) {
        Objects.checkIndex(index, length);
        array[index] = value;
    }

    @Override
    public void insert(int index, int value) {
        int newLength = length + 1;
        Objects.checkIndex(index, newLength);
        if (newLength > array.length) {
            throw new CollectionFullException("List is full");
        }
        for (int i = length; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = value;
        length = newLength;
    }

    @Override
    public int remove(int index) {
        Objects.checkIndex(index, length);
        int result = array[index];
        for (int i = index + 1; i < length; i++) {
            array[i - 1] = array[i];
        }
        length--;
        return result;
    }

    @Override
    public void clear() {
        length = 0;
    }

    @Override
    public int find(int value) {
        for (int i = 0; i < length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void traverse(IntConsumer callback) {
        for (int i = 0; i < length; i++) {
            callback.accept(array[i]);
        }
    }
}
