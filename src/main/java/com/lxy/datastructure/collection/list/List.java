package com.lxy.datastructure.collection.list;

import com.lxy.datastructure.collection.common.Collection;
import com.lxy.datastructure.collection.common.CollectionFullException;

import java.util.function.IntConsumer;

/**
 * 线性表（抽象数据类型）
 */
public interface List extends Collection {
    /**
     * 获取线性表第i个位置的值
     * @param index 线性表位置i
     * @return 线性表中第i个位置的值
     * @throws IndexOutOfBoundsException 如果i < 0或i &ge; {@link List#size()}
     */
    int get(int index);
    /**
     * 设置线性表第i个位置的值
     * @param index 线性表位置i
     * @param value 要设置的值
     * @throws IndexOutOfBoundsException 如果i < 0或i &ge; {@link #size()}
     */
    void set(int index, int value);
    /**
     * 在线性表第i个位置的值插入值
     * @param index 线性表位置i
     * @param value 要插入的值
     * @throws IndexOutOfBoundsException 如果i < 0或i &ge; {@link #size()}
     * @throws CollectionFullException 如果线性表已满
     */
    void insert(int index, int value);
    /**
     * 删除线性表第i个位置的值
     * @param index 线性表位置i
     * @return value 线性表第i个位置的值
     * @throws IndexOutOfBoundsException 如果i < 0或i > {@link #size()}
     */
    int remove(int index);
    /**
     * 清空线性表
     */
    void clear();
    /**
     * 在线性表中查找指定值
     * @param value 待查找的值
     * @return 待查找的值所在位置（查找失败返回-1）
     */
    int find(int value);
    /**
     * 遍历线性表
     * @param callback 线性表遍历回调接口
     */
    void traverse(IntConsumer callback);
}
