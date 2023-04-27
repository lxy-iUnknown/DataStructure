package com.lxy.datastructure.collection.queue;

import com.lxy.datastructure.collection.common.CollectionEmptyException;
import com.lxy.datastructure.collection.common.Node;
import com.lxy.datastructure.collection.util.Util;

public class LinkedQueue implements Queue {
    // 队头为链表头节点，队尾为链表最后一个结点
    private final Node front;
    private Node rear;
    private int size;

    public LinkedQueue() {
        Node head = Util.createHeadNode();
        front = head;
        rear = head;
    }

    private void ensureNotEmpty() {
        if (size <= 0) {
            throw new CollectionEmptyException("Queue is empty");
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void enqueue(int value) {
        rear = Util.insertAndReturnNextNode(rear, value);
        size++;
    }

    @Override
    public int dequeue() {
        ensureNotEmpty();
        Node next = Util.removeNextNode(front);
        size--;
        if (size == 0) {
            // 最后一个结点出队，修改队尾指针
            rear = front;
        }
        return next.value;
    }

    @Override
    public int front() {
        ensureNotEmpty();
        return front.next.value;
    }
}
