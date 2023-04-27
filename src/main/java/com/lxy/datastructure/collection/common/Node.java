package com.lxy.datastructure.collection.common;

public class Node {
    public int value;
    public Node next;

    public Node(int value) {
        this.value = value;
    }

    public static Node findNode(Node start, int index) {
        Node node = start;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }
}