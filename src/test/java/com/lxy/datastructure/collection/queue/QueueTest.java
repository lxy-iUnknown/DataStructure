package com.lxy.datastructure.collection.queue;

import com.lxy.datastructure.collection.CollectionTest;
import com.lxy.datastructure.collection.common.CollectionEmptyException;
import com.lxy.datastructure.collection.common.CollectionFullException;
import com.lxy.datastructure.collection.util.TestUtil;
import com.lxy.datastructure.util.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public abstract class QueueTest extends CollectionTest<Queue> {
    private Queue populateQueue() {
        var queue = createCollection();
        for (int i = 0; i < Constants.COLLECTION_CAPACITY; i++) {
            int finalI = i;
            Assertions.assertDoesNotThrow(() -> queue.enqueue(
                            TestUtil.element(finalI)),
                    "[Queue.enqueue]An exception is thrown");
        }
        return queue;
    }

    @Test
    public void testQueueIsEmpty() {
        Assertions.assertTrue(createCollection().isEmpty(),
                "[Queue.isEmpty]Stack is not empty");
        Assertions.assertFalse(populateQueue().isEmpty(),
                "[Queue.isEmpty]Stack is empty");
    }

    @Test
    public void testQueueSize() {
        Queue queue = createCollection();
        Assertions.assertEquals(0, queue.size(),
                "[Queue.size]Zero length failed");
        Assertions.assertTrue(queue.isEmpty(),
                "[Queue.isEmpty]Queue is not empty");
        Assertions.assertEquals(Constants.COLLECTION_CAPACITY, populateQueue().size(),
                "[Queue.size]New length failed");
    }

    @Test
    public void testQueueFull() {
        if (isFixedSize()) {
            Assertions.assertThrowsExactly(CollectionFullException.class,
                    () -> populateQueue().enqueue(100),
                    "[Queue.enqueue]No exception is thrown");
        }
    }

    @Test
    public void testEmptyDequeueReadFrontOutOfRange() {
        var queue = createCollection();
        Assertions.assertThrowsExactly(CollectionEmptyException.class, queue::dequeue,
                "[Queue.dequeue]No exception is thrown");
        Assertions.assertThrowsExactly(CollectionEmptyException.class, queue::front,
                "[Queue.front]No exception is thrown");
    }

    @Test
    public void testDequeue() {
        var queue = populateQueue();
        for (int i = 0; i < Constants.COLLECTION_CAPACITY; i++) {
            Assertions.assertEquals(TestUtil.element(i), queue.dequeue(),
                    "[Queue.dequeue]Elements are not equal");
        }
    }

    @Test
    public void testFrontAndDequeue() {
        var queue = populateQueue();
        for (int i = 0; i < Constants.COLLECTION_CAPACITY; i++) {
            int expected = TestUtil.element(i);
            int actual1 = queue.front();
            int actual2 = queue.dequeue();
            Assertions.assertEquals(expected, actual1,
                    "[Queue.front]Elements do not equal");
            Assertions.assertEquals(expected, actual2,
                    "[Queue.dequeue]Elements do not equal");
        }
        Assertions.assertEquals(0, queue.size(),
                "[Queue.size]Queue is not empty");
        Assertions.assertTrue(queue.isEmpty(),
                "[Queue.isEmpty]Queue is not empty");
    }

    @Test
    public void testEnqueueAndDequeue() {
        final int SIZE = Constants.COLLECTION_CAPACITY / 3;

        var queue = populateQueue();
        for (int i = 0; i < SIZE; i++) {
            Assertions.assertEquals(TestUtil.element(i), queue.dequeue(),
                    "[Queue.dequeue]Elements are not equal");
        }
        for (int i = 0; i < SIZE; i++) {
            int finalI = i;
            Assertions.assertDoesNotThrow(() -> queue.enqueue(
                            TestUtil.newElement(finalI)),
                    "[Queue.enqueue]An exception is thrown");
        }
        Assertions.assertEquals(Constants.COLLECTION_CAPACITY, queue.size(),
                "[Queue.size]Size not equal");
        for (int i = SIZE; i < Constants.COLLECTION_CAPACITY; i++) {
            Assertions.assertEquals(TestUtil.element(i), queue.dequeue(),
                    "[Queue.dequeue]Elements are not equal");
        }
        for (int i = 0; i < SIZE; i++) {
            Assertions.assertEquals(TestUtil.newElement(i), queue.dequeue(),
                    "[Queue.dequeue]Elements are not equal");
        }
        Assertions.assertEquals(0, queue.size(),
                "[Queue.size]Queue is not empty");
        Assertions.assertTrue(queue.isEmpty(),
                "[Queue.isEmpty]Queue is not empty");
    }
}