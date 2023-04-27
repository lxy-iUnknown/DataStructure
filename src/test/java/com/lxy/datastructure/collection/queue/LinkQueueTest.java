package com.lxy.datastructure.collection.queue;

public class LinkQueueTest extends QueueTest {
    @Override
    protected Queue createCollection() {
        return new LinkedQueue();
    }

    @Override
    protected boolean isFixedSize() {
        return false;
    }
}
