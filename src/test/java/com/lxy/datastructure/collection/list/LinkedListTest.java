package com.lxy.datastructure.collection.list;

public class LinkedListTest extends ListTest {
    @Override
    protected List createCollection() {
        return new LinkedList();
    }

    @Override
    protected boolean isFixedSize() {
        return false;
    }
}
