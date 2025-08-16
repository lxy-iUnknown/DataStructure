package com.lxy.datastructure.collection.stack;

import com.lxy.datastructure.collection.common.Node;
import com.lxy.datastructure.collection.common.NodeUtil;
import com.lxy.datastructure.collection.exception.CollectionEmptyException;
import com.lxy.datastructure.expression.Stack;

public class LinkedStack implements Stack {
    // 栈顶指针为链表头节点
    private final Node head = NodeUtil.createHeadNode();
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void push(int value) {
        NodeUtil.insertNextNode(head, value);
        size++;
    }

    @Override
    public int pop() {
        if (isEmpty()) {
            CollectionEmptyException.throwIt("Stack");
        }
        var topNode = NodeUtil.removeNextNode(head);
        size--;
        return topNode.value;
    }

    @Override
    public int peek() {
        if (isEmpty()) {
            CollectionEmptyException.throwIt("Stack");
        }
        return head.next.value;
    }
}
