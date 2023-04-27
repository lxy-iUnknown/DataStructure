package com.lxy.datastructure.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class SortTest {
    private record RandomArray(int[] array, int[] sorted) {
    }

    private static final int MIN_LENGTH = 10;
    private static final int MAX_LENGTH = 100;
    private static final int MIN_ARRAY_VALUE = -100;
    private static final int MAX_ARRAY_VALUE = 100;

    private static void reverse(int[] array, int fromIndex, int toIndex) {
        int i = fromIndex, j = toIndex - 1;
        while (i < j) {
            SortUtil.swap(array, i++, j--);
        }
    }

    private static void sort(int[] array, int fromIndex, int toIndex,
                                      boolean ascending) {
        Arrays.sort(array, fromIndex, toIndex);
        if (!ascending) {
            reverse(array, fromIndex, toIndex);
        }
    }

    private static void reverse(int[] array) {
        reverse(array, 0, array.length);
    }

    private static int randomRangeClosed(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    private static int[] randomArrayInternal() {
        int length = randomRangeClosed(MIN_LENGTH, MAX_LENGTH);
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = randomRangeClosed(MIN_ARRAY_VALUE, MAX_ARRAY_VALUE);
        }
        return array;
    }

    private static RandomArray randomArray() {
        int[] array = randomArrayInternal();
        int[] sorted = array.clone();
        Arrays.sort(sorted);
        return new RandomArray(array, sorted);
    }

    private static RandomArray randomSortedArray(boolean ascending) {
        int[] array = randomArrayInternal();
        Arrays.sort(array);
        int[] sorted = array.clone();
        if (!ascending) {
            reverse(array);
        }
        return new RandomArray(array, sorted);
    }

    private static RandomArray randomPartialSortedArray(boolean ascending) {
        RandomArray randomArray = randomArray();
        int[] array = randomArray.array;
        int length = array.length;
        int s1 = length / 4, s2 = length / 2, s3 = length * 3 / 4;
        sort(array, 0, s1, ascending);
        sort(array, s1, s2, ascending);
        sort(array, s2, s3, ascending);
        sort(array, s3, length, ascending);
        return randomArray;
    }

    private static void sortTestArray(Consumer<int[]> sortFunction, RandomArray randomArray) {
        sortFunction.accept(randomArray.array);
        Assertions.assertArrayEquals(randomArray.sorted, randomArray.array);
    }

    public static Stream<RandomArray> arguments() {
        return Stream.of(
                randomArray(),
                randomSortedArray(true),
                randomSortedArray(false),
                randomPartialSortedArray(true),
                randomPartialSortedArray(false)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    public void bubbleSortTest(RandomArray randomArray) {
        sortTestArray(BubbleSort::sort, randomArray);
    }

    @ParameterizedTest
    @MethodSource("arguments")
    public void heapSortTest(RandomArray randomArray) {
        sortTestArray(HeapSort::sort, randomArray);
    }

    @ParameterizedTest
    @MethodSource("arguments")
    public void insertionSortTest(RandomArray randomArray) {
        sortTestArray(InsertionSort::sort, randomArray);
    }

    @ParameterizedTest
    @MethodSource("arguments")
    public void mergeSortTest(RandomArray randomArray) {
        sortTestArray(MergeSort::sort, randomArray);
    }

    @ParameterizedTest
    @MethodSource("arguments")
    public void quickSortTest(RandomArray randomArray) {
        sortTestArray(QuickSort::sort, randomArray);
    }

    @ParameterizedTest
    @MethodSource("arguments")
    public void selectionSortTest(RandomArray randomArray) {
        sortTestArray(SelectionSort::sort, randomArray);
    }

    @ParameterizedTest
    @MethodSource("arguments")
    public void shellSortTest(RandomArray randomArray) {
        sortTestArray(ShellSort::sort, randomArray);
    }
}
