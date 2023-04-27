package com.lxy.datastructure.collection.stack;

import com.lxy.datastructure.collection.common.CollectionEmptyException;
import com.lxy.datastructure.collection.common.Node;
import com.lxy.datastructure.collection.util.Util;

public class LinkedStack implements Stack {
    // 栈顶指针为链表头节点
    private final Node head = Util.createHeadNode();
    private int top;

    private void ensureNotEmpty() {
        if (top <= 0) {
            throw new CollectionEmptyException("Stack is empty");
        }
    }

    @Override
    public int size() {
        return top;
    }

    @Override
    public void push(int value) {
        Util.insertNextNode(head, value);
        top++;
    }

    @Override
    public int pop() {
        ensureNotEmpty();
        Node topNode = Util.removeNextNode(head);
        top--;
        return topNode.value;
    }

    @Override
    public int peek() {
        ensureNotEmpty();
        return head.next.value;
    }
}
