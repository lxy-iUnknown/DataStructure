package com.lxy.datastructure.collection.exception;

/**
 * 该异常表示集合已满
 */
public class CollectionFullException extends RuntimeException {
    public CollectionFullException(String message) {
        super(message);
    }

    public static void throwIt(String name) {
        throw new CollectionFullException(name + " is full");
    }
}
