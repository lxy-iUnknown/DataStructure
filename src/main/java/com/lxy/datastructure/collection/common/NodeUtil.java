package com.lxy.datastructure.collection.common;

public class NodeUtil {
    public static Node createHeadNode() {
        return new Node(Integer.MAX_VALUE);
    }

    public static void insertNextNode(Node node, int value) {
        var next = new Node(value);
        next.next = node.next;
        node.next = next;
    }

    public static Node insertAndReturnNextNode(Node node, int value) {
        var next = new Node(value);
        next.next = node.next;
        node.next = next;
        return next;
    }

    public static Node removeNextNode(Node node) {
        var next = node.next;
        node.next = next.next;
        return next;
    }
}
