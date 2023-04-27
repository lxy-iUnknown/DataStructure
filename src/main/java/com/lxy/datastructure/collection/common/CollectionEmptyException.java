package com.lxy.datastructure.collection.common;

/**
 * 该异常表示集合已空
 */
public class CollectionEmptyException extends RuntimeException {
    public CollectionEmptyException() {
    }

    public CollectionEmptyException(String message) {
        super(message);
    }

    public CollectionEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public CollectionEmptyException(Throwable cause) {
        super(cause);
    }

    public CollectionEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
