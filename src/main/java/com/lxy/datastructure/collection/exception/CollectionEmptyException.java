package com.lxy.datastructure.collection.exception;

/**
 * 该异常表示集合已空
 */
public class CollectionEmptyException extends RuntimeException {
    public CollectionEmptyException(String message) {
        super(message);
    }

    public static void throwIt(String name) {
        throw new CollectionEmptyException(name + " is empty");
    }
}
