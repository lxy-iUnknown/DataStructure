package com.lxy.datastructure.collection.list;

import com.lxy.datastructure.collection.CollectionTest;
import com.lxy.datastructure.collection.exception.CollectionFullException;
import com.lxy.datastructure.collection.util.TestUtil;
import com.lxy.datastructure.util.Constants;
import com.lxy.datastructure.util.MutableInt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public abstract class ListTest extends CollectionTest<List> {

    private static final int INDEX = Constants.COLLECTION_CAPACITY / 3;

    private static void removeAll(List list, int length) {
        for (int i = 0; i < length; i++) {
            Assertions.assertDoesNotThrow(() -> list.remove(0),
                    "[List.remove]An exception is thrown");
        }
    }

    private static void listGetOutOfRange(int index, List list) {
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class,
                () -> list.get(index),
                "[List.get]No exception is thrown");
    }

    private static void listSetOutOfRange(int index, List list) {
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class,
                () -> list.set(index, 100),
                "[List.set]No exception is thrown");
    }

    private static void listInsertOutOfRange(int index, List list) {
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class,
                () -> list.insert(index, 100),
                "[List.insert]No exception is thrown");
    }

    private static void listRemoveOutOfRange(int index, List list) {
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class,
                () -> list.remove(index),
                "[List.remove]No exception is thrown");
    }

    private List popluateList() {
        var list = createCollection();
        for (int i = 0; i < Constants.COLLECTION_CAPACITY; i++) {
            int finalI = i;
            Assertions.assertDoesNotThrow(() -> list.insert(finalI,
                            TestUtil.element(finalI)),
                    "[List.insert]An exception is thrown");
        }
        return list;
    }

    @Test
    public void testListSize() {
        List list = createCollection();
        Assertions.assertEquals(0, list.size(),
                "[List.size]Zero length failed");
        Assertions.assertTrue(list.isEmpty(),
                "[List.isEmpty]List is not empty");
        Assertions.assertEquals(Constants.COLLECTION_CAPACITY, popluateList().size(),
                "[List.size]New length failed");
    }

    @Test
    public void testListIsEmpty() {
        Assertions.assertTrue(createCollection().isEmpty(),
                "[List.isEmpty]List is not empty");
        Assertions.assertFalse(popluateList().isEmpty(),
                "[List.isEmpty]List is empty");
    }

    @Test
    public void testListFull() {
        if (isFixedSize()) {
            Assertions.assertThrowsExactly(CollectionFullException.class,
                    () -> popluateList().insert(Constants.COLLECTION_CAPACITY, 100),
                    "[List.insert]No exception is thrown");
        }
    }

    @Test
    public void testListGetSet() {
        var list = popluateList();
        for (int i = 0; i < Constants.COLLECTION_CAPACITY; i++) {
            Assertions.assertEquals(TestUtil.element(i),
                    list.get(i), "[List.get]Elements do not equal");
            int finalI = i;
            Assertions.assertDoesNotThrow(() -> list.set(finalI,
                            TestUtil.newElement(finalI)),
                    "[List.set]An exception is thrown");
        }
        for (int i = 0; i < Constants.COLLECTION_CAPACITY; i++) {
            Assertions.assertEquals(TestUtil.newElement(i),
                    list.get(i), "[List.set]Failed to change value");
        }
    }

    @Test
    public void testListFind() {
        var list = popluateList();
        int index;
        index = list.find(-2);
        Assertions.assertEquals(-1, index,
                "[List.find]Non-existed element failed");
        index = list.find(TestUtil.element(INDEX));
        Assertions.assertEquals(INDEX, index,
                "[List.find]Existed element failed");
    }

    @Test
    public void testListOutOfRange() {
        var list = popluateList();

        listGetOutOfRange(Constants.COLLECTION_CAPACITY, list);
        listGetOutOfRange(Constants.COLLECTION_CAPACITY + 1, list);
        listGetOutOfRange(-1, list);

        listSetOutOfRange(Constants.COLLECTION_CAPACITY, list);
        listSetOutOfRange(Constants.COLLECTION_CAPACITY + 1, list);
        listSetOutOfRange(-1, list);

        listInsertOutOfRange(Constants.COLLECTION_CAPACITY * 2, list);
        listInsertOutOfRange(Constants.COLLECTION_CAPACITY * 3, list);
        listInsertOutOfRange(-1, list);

        listRemoveOutOfRange(Constants.COLLECTION_CAPACITY, list);
        listRemoveOutOfRange(Constants.COLLECTION_CAPACITY + 1, list);
        listRemoveOutOfRange(-1, list);
    }

    @Test
    public void testRemove() {
        var list = popluateList();
        MutableInt value = new MutableInt();
        for (int i = INDEX; i < Constants.COLLECTION_CAPACITY; i++) {
            Assertions.assertDoesNotThrow(() -> value.setValue(list.remove(INDEX)),
                    "[List.remove]An exception is thrown");
            Assertions.assertEquals(TestUtil.element(i),
                    value.getValue(), "[List.remove]Incorrect value");
        }
        Assertions.assertEquals(INDEX, list.size(),
                "[List.size]Incorrect length after removing elements");
        removeAll(list, INDEX);
        Assertions.assertEquals(0, list.size(),
                "[List.size]Nonzero list");
        Assertions.assertTrue(list.isEmpty(),
                "[List.isEmpty]List is not empty");
    }

    @Test
    public void testClear() {
        var list = popluateList();
        list.clear();
        listGetOutOfRange(1, list);
        listGetOutOfRange(-1, list);

        listSetOutOfRange(1, list);
        listSetOutOfRange(-1, list);

        listInsertOutOfRange(1, list);
        listInsertOutOfRange(-1, list);

        listRemoveOutOfRange(1, list);
        listRemoveOutOfRange(-1, list);
        Assertions.assertEquals(0, list.size(),
                "[List.length]Nonzero list");
        Assertions.assertTrue(list.isEmpty(),
                "[List.isEmpty]List is not empty");
    }

    @Test
    public void testRemoveThenInsert() {
        var list = popluateList();
        removeAll(list, Constants.COLLECTION_CAPACITY);
        for (int i = 0; i < Constants.COLLECTION_CAPACITY; i++) {
            int finalI = i;
            Assertions.assertDoesNotThrow(() -> list.insert(finalI,
                            TestUtil.element(finalI + Constants.COLLECTION_CAPACITY)),
                    "[List.insert]An exception is thrown");
        }
        for (int i = 0; i < Constants.COLLECTION_CAPACITY; i++) {
            Assertions.assertEquals(TestUtil.element(i + Constants.COLLECTION_CAPACITY),
                    list.get(i), "[List.get]Elements do not equal");
        }
        list.clear();
        Assertions.assertEquals(0, list.size(),
                "[List.size]Nonzero list");
        Assertions.assertTrue(list.isEmpty(),
                "[List.isEmpty]List is not empty");
    }

    @Test
    public void testListTraverse() {
        var list = popluateList();
        MutableInt index = new MutableInt();
        list.traverse(value -> {
            Assertions.assertEquals(TestUtil.element(index.getValue()),
                    value, "[List.traverse]Elements do not equal");
            index.increment();
        });
        Assertions.assertEquals(Constants.COLLECTION_CAPACITY, index.getValue(),
                "[List.traverse]Traverse is not implemented");
    }
}
