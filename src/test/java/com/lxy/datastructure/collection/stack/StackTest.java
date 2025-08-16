package com.lxy.datastructure.collection.stack;

import com.lxy.datastructure.collection.CollectionTest;
import com.lxy.datastructure.collection.exception.CollectionEmptyException;
import com.lxy.datastructure.collection.exception.CollectionFullException;
import com.lxy.datastructure.collection.util.TestUtil;
import com.lxy.datastructure.expression.Stack;
import com.lxy.datastructure.util.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public abstract class StackTest extends CollectionTest<Stack> {

    private Stack populateStack() {
        var stack = createCollection();
        for (int i = 0; i < Constants.COLLECTION_CAPACITY; i++) {
            int finalI = i;
            Assertions.assertDoesNotThrow(() -> stack.push(
                            TestUtil.element(finalI)),
                    "[Stack.push]An exception is thrown");
        }
        return stack;
    }

    @Test
    public void testStackIsEmpty() {
        Assertions.assertTrue(createCollection().isEmpty(),
                "[Stack.isEmpty]Stack is not empty");
        Assertions.assertFalse(populateStack().isEmpty(),
                "[Stack.isEmpty]Stack is empty");
    }

    @Test
    public void testStackSize() {
        Stack stack = createCollection();
        Assertions.assertEquals(0, stack.size(),
                "[Stack.size]Zero length failed");
        Assertions.assertTrue(stack.isEmpty(),
                "[Stack.isEmpty]Stack is not empty");
        Assertions.assertEquals(Constants.COLLECTION_CAPACITY, populateStack().size(),
                "[Stack.size]New length failed");
    }

    @Test
    public void testEmptyPeekPopOutOfRange() {
        var stack = createCollection();
        Assertions.assertThrowsExactly(CollectionEmptyException.class, stack::pop,
                "[Stack.pop]No exception is thrown");
        Assertions.assertThrowsExactly(CollectionEmptyException.class, stack::peek,
                "[Stack.pop]No exception is thrown");
    }

    @Test
    public void testStackFull() {
        if (isFixedSize()) {
            Assertions.assertThrowsExactly(CollectionFullException.class,
                    () -> populateStack().push(100),
                    "[Stack.push]No exception is thrown");
        }
    }

    @Test
    public void testPeekAndPop() {
        var stack = populateStack();
        for (int i = Constants.COLLECTION_CAPACITY - 1; i >= 0; i--) {
            int expected = TestUtil.element(i);
            int actual1 = stack.peek();
            int actual2 = stack.pop();
            Assertions.assertEquals(expected, actual1,
                    "[Stack.peek]Elements do not equal");
            Assertions.assertEquals(expected, actual2,
                    "[Stack.pop]Elements do not equal");
        }
        Assertions.assertEquals(0, stack.size(),
                "[Stack.size]Non-zero stack");
        Assertions.assertTrue(stack.isEmpty(),
                "[Stack.isEmpty]Stack is not empty");
    }

    @Test
    public void testPushAndPop() {
        final int STACK_VALUE1 = 123456789;
        final int STACK_VALUE2 = 234567890;
        final int STACK_VALUE3 = 345678901;

        var stack = createCollection();

        stack.push(STACK_VALUE1);
        stack.push(STACK_VALUE2);
        stack.push(STACK_VALUE3);
        Assertions.assertEquals(STACK_VALUE3, stack.pop(),
                "[Stack.peek]Elements do not equal");
        Assertions.assertEquals(STACK_VALUE2, stack.pop(),
                "[Stack.peek]Elements do not equal");
        Assertions.assertEquals(STACK_VALUE1, stack.pop(),
                "[Stack.peek]Elements do not equal");

        stack.push(STACK_VALUE1);
        Assertions.assertEquals(STACK_VALUE1, stack.pop(),
                "[Stack.peek]Elements do not equal");
        stack.push(STACK_VALUE2);
        Assertions.assertEquals(STACK_VALUE2, stack.pop(),
                "[Stack.peek]Elements do not equal");
        stack.push(STACK_VALUE3);
        Assertions.assertEquals(STACK_VALUE3, stack.pop(),
                "[Stack.peek]Elements do not equal");

        Assertions.assertEquals(0, stack.size(),
                "[Stack.size]Non-zero stack");
        Assertions.assertTrue(stack.isEmpty(),
                "[Stack.isEmpty]Stack is not empty");
    }
}
