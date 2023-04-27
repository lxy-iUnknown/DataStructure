package com.lxy.datastructure.collection.list;

import com.lxy.datastructure.collection.common.CollectionEmptyException;
import com.lxy.datastructure.collection.common.Node;
import com.lxy.datastructure.collection.util.Util;

import java.util.Objects;
import java.util.function.IntConsumer;

public class LinkedList implements List {

    private final Node head = Util.createHeadNode();
    private int length;

    private Node findHeadNode(int index) {
        return Node.findNode(head, index);
    }

    private Node findNonHeadNode(int index) {
        return Node.findNode(head.next, index);
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public int get(int index) {
        Objects.checkIndex(index, length);
        return findNonHeadNode(index).value;
    }

    @Override
    public void set(int index, int value) {
        Objects.checkIndex(index, length);
        findNonHeadNode(index).value = value;
    }

    @Override
    public void insert(int index, int value) {
        int newLength = length + 1;
        Objects.checkIndex(index, newLength);
        Util.insertNextNode(findHeadNode(index), value);
        length = newLength;
    }

    @Override
    public int remove(int index) {
        Objects.checkIndex(index, length);
        Node next = Util.removeNextNode(findHeadNode(index));
        length--;
        return next.value;
    }

    @Override
    public void clear() {
        head.next = null;
        length = 0;
    }

    @Override
    public int find(int value) {
        Node node = head.next;
        for (int i = 0; i < length; i++) {
            if (node.value == value) {
                return i;
            }
            node = node.next;
        }
        return -1;
    }

    @Override
    public void traverse(IntConsumer callback) {
        Node node = head.next;
        for (int i = 0; i < length; i++) {
            callback.accept(node.value);
            node = node.next;
        }
    }
}
