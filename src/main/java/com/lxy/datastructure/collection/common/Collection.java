package com.lxy.datastructure.collection.common;

/**
 * 集合（抽象数据类型）
 */
public interface Collection {
    /**
     * 获取集合中的元素数量
     * @return 集合中的元素数量
     */
    int size();
    /**
     * 判断集合是否非空
     * @return 集合是否非空
     */
    default boolean isEmpty() {
        return size() == 0;
    }
}
