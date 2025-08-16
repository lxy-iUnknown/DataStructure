package com.lxy.datastructure.collection.stack;

import com.lxy.datastructure.expression.Stack;

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
