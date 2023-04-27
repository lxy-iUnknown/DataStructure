package com.lxy.datastructure.collection.stack;

public class SequenceStackTest extends StackTest {
    @Override
    protected Stack createCollection() {
        return new SequenceStack();
    }

    @Override
    protected boolean isFixedSize() {
        return true;
    }
}
