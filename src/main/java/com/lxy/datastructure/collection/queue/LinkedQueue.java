package com.lxy.datastructure.collection.queue;

import com.lxy.datastructure.collection.common.Node;
import com.lxy.datastructure.collection.common.NodeUtil;
import com.lxy.datastructure.collection.exception.CollectionEmptyException;

public class LinkedQueue implements Queue {
    // 队头为链表头节点，队尾为链表最后一个结点
    private final Node front;
    private Node rear;
    private int size;

    public LinkedQueue() {
        var head = NodeUtil.createHeadNode();
        front = head;
        rear = head;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void enqueue(int value) {
        rear = NodeUtil.insertAndReturnNextNode(rear, value);
        size++;
    }

    @Override
    public int dequeue() {
        if (isEmpty()) {
            CollectionEmptyException.throwIt("Queue");
        }
        var next = NodeUtil.removeNextNode(front);
        size--;
        if (size == 0) {
            // 最后一个结点出队，修改队尾指针
            rear = front;
        }
        return next.value;
    }

    @Override
    public int front() {
        if (isEmpty()) {
            CollectionEmptyException.throwIt("Queue");
        }
        return front.next.value;
    }
}
