package com.lxy.datastructure.collection.util;

import com.lxy.datastructure.collection.common.Node;

public class Util {
    private static class HeadNode extends Node {

        public HeadNode(int value) {
            super(value);
        }
    }

    public static Node createHeadNode() {
        return new HeadNode(Integer.MAX_VALUE);
    }


    public static void insertNextNode(Node node, int value) {
        Node next = new Node(value);
        next.next = node.next;
        node.next = next;
    }

    public static Node insertAndReturnNextNode(Node node, int value) {
        Node next = new Node(value);
        next.next = node.next;
        node.next = next;
        return next;
    }

    public static Node removeNextNode(Node node) {
        Node next = node.next;
        node.next = next.next;
        return next;
    }
}
