package com.lxy.datastructure.collection;

import com.lxy.datastructure.collection.common.Collection;

public abstract class CollectionTest<T extends Collection> {
    protected abstract T createCollection();
    protected abstract boolean isFixedSize();
}
