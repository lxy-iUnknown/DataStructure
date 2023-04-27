package com.lxy.datastructure.collection.stack;

public class LinkedStackTest extends StackTest {

    @Override
    protected Stack createCollection() {
        return new LinkedStack();
    }

    @Override
    protected boolean isFixedSize() {
        return false;
    }
}
