package com.lxy.datastructure.collection.common;

/**
 * 该异常表示集合已满
 */
public class CollectionFullException extends RuntimeException {
    public CollectionFullException() {
    }

    public CollectionFullException(String message) {
        super(message);
    }

    public CollectionFullException(String message, Throwable cause) {
        super(message, cause);
    }

    public CollectionFullException(Throwable cause) {
        super(cause);
    }

    public CollectionFullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
