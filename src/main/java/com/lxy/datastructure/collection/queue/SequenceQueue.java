package com.lxy.datastructure.collection.queue;

import com.lxy.datastructure.collection.common.CollectionEmptyException;
import com.lxy.datastructure.collection.common.CollectionFullException;
import com.lxy.datastructure.util.Constants;

public class SequenceQueue implements Queue {
    private static final int MAX_SIZE = Constants.COLLECTION_CAPACITY + 1 /* 牺牲一个存储单元*/;

    private final int[] array;
    private int front, rear;

    public SequenceQueue() {
        array = new int[MAX_SIZE];
    }

    // Perform Math.floorMod(i + 1, MAX_SIZE)
    private static int incrementMod(int i) {
        return ++i >= MAX_SIZE ? 0 : i;
    }

    // Perform Math.floorMod(j - i, MAX_SIZE)
    private static int subtractMod(int i, int j) {
        if ((i -= j) < 0) {
            i += MAX_SIZE;
        }
        return i;
    }

    private void ensureNotEmpty() {
        if (isEmpty()) {
            throw new CollectionEmptyException("Queue is empty");
        }
    }

    @Override
    public int size() {
        return subtractMod(rear, front);
    }

    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    @Override
    public void enqueue(int value) {
        int newRear = incrementMod(rear);
        if (newRear == front) {
            throw new CollectionFullException("Queue is full");
        }
        array[rear] = value;
        rear = newRear;
    }

    @Override
    public int dequeue() {
        ensureNotEmpty();
        int value = front();
        front = incrementMod(front);
        return value;
    }

    @Override
    public int front() {
        ensureNotEmpty();
        return array[front];
    }
}
