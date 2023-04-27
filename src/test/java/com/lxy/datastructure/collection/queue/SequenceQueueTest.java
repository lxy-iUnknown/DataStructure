package com.lxy.datastructure.collection.queue;

public class SequenceQueueTest extends QueueTest {
    @Override
    protected Queue createCollection() {
        return new SequenceQueue();
    }

    @Override
    protected boolean isFixedSize() {
        return true;
    }
}
