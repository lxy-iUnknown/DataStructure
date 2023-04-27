package com.lxy.datastructure.collection.list;

public class SequenceListTest extends ListTest {
    @Override
    protected List createCollection() {
        return new SequenceList();
    }

    @Override
    protected boolean isFixedSize() {
        return true;
    }
}
