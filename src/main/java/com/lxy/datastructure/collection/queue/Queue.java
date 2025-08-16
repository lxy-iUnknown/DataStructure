package com.lxy.datastructure.collection.queue;

import com.lxy.datastructure.collection.common.Collection;
import com.lxy.datastructure.collection.exception.CollectionEmptyException;
import com.lxy.datastructure.collection.exception.CollectionFullException;

/**
 * 队列（抽象数据类型）
 */
public interface Queue extends Collection {
    /**
     * 在队尾插入元素
     *
     * @param value 新的队尾元素
     * @throws CollectionFullException 如果队列已满
     */
    void enqueue(int value);

    /**
     * 将队头元素出队
     *
     * @return 队头元素
     * @throws CollectionEmptyException 如果队列为空，即{@link #isEmpty()}返回{@code true}
     */
    int dequeue();

    /**
     * 获取队头元素
     *
     * @return 队头元素
     * @throws CollectionEmptyException 如果队列为空，即{@link #isEmpty()}返回{@code true}
     */
    int front();
}
