package com.lxy.datastructure.expression;

import com.lxy.datastructure.collection.common.Collection;
import com.lxy.datastructure.collection.exception.CollectionEmptyException;
import com.lxy.datastructure.collection.exception.CollectionFullException;

/**
 * 栈（抽象数据类型）
 */
public interface Stack extends Collection {
    /**
     * 入栈操作
     *
     * @param value 新的栈顶元素
     * @throws CollectionFullException 如果栈已满
     */
    void push(int value);

    /**
     * 进行出栈操作
     *
     * @return 栈顶元素
     * @throws CollectionEmptyException 如果栈为空，即{@link #isEmpty()}返回{@code true}
     */
    int pop();

    /**
     * 读取栈顶元素的值
     *
     * @return 栈顶元素
     * @throws CollectionEmptyException 如果栈为空，即{@link #isEmpty()}返回{@code true}
     */
    int peek();
}
