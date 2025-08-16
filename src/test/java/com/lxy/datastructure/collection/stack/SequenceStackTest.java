package com.lxy.datastructure.collection.stack;

import com.lxy.datastructure.expression.Stack;

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
