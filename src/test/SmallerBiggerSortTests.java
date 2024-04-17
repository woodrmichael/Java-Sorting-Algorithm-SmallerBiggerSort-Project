/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 12 - Smaller Bigger Sort
 * Name: Michael Wood
 * Created: 4/11/2024
 */
package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import woodm.SmallerBiggerSort;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A bundle of test methods used to test the sorting methods in SmallerBiggerSort
 */
class SmallerBiggerSortTests {
    private List<Integer> list;
    private List<Integer> list2;
    private List<Integer> list3;
    private List<Integer> alreadySortedList;
    private List<Integer> nearlySortedList;
    private List<Integer> reverseSortedList;

    @BeforeEach
    void setUp() {
        list = new ArrayList<>(Arrays.asList(8, 3, 8, 5, 12, 1, 8, 18, 13));
        list2 = new ArrayList<>(Arrays.asList(1, 1, 4, 2, 100, 5, 3, 5, 97));
        list3 = new ArrayList<>(Arrays.asList(10, 3, 4, 1, 2, 1, 6, 7, 5));
        alreadySortedList = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 4, 5, 10, 11, 15));
        nearlySortedList = new ArrayList<>(Arrays.asList(1, 1, 5, 3, 4, 2, 11, 10, 15));
        reverseSortedList = new ArrayList<>(Arrays.asList(15, 10, 11, 5, 4, 3, 2, 1, 1));
    }

    @Test
    void smallerBiggerTest() {
        assertThrows(IllegalArgumentException.class, () ->
                SmallerBiggerSort.smallerBigger(list, 1, 0));
        assertThrows(IllegalArgumentException.class, () ->
                SmallerBiggerSort.smallerBigger(list, 0, 0));
        assertThrows(IllegalArgumentException.class, () ->
                SmallerBiggerSort.smallerBigger(list, 0, list.size() + 1));
        assertThrows(IllegalArgumentException.class, () ->
                SmallerBiggerSort.smallerBigger(list, -1, list.size()));
        assertThrows(IllegalArgumentException.class, () ->
                SmallerBiggerSort.smallerBigger(list, 0, -1));

        List<Integer> temp = new ArrayList<>(list);
        assertEquals(4, SmallerBiggerSort.smallerBigger(temp, 2, 6));
        assertTrue(checkSmallerBigger(temp, 2, 6, 4));
        temp = new ArrayList<>(list);
        assertEquals(5, SmallerBiggerSort.smallerBigger(temp, 0, list.size()));
        assertTrue(checkSmallerBigger(temp, 0, temp.size(), 4));
        temp = new ArrayList<>(list);
        assertEquals(2, SmallerBiggerSort.smallerBigger(temp, 1, list.size()));
        assertTrue(checkSmallerBigger(temp, 1, temp.size(), 2));
        temp = new ArrayList<>(list);
        assertEquals(8, SmallerBiggerSort.smallerBigger(temp, 7, list.size()));
        assertTrue(checkSmallerBigger(temp, 7, temp.size(), 8));
    }

    private boolean checkSmallerBigger(List<Integer> list,
                                       int startIndex, int endIndex, int retIndex) {
        boolean inOrder = true;
        Integer value = list.get(retIndex);
        for(int i = startIndex; inOrder && i < retIndex; i++) {
            if(list.get(i).compareTo(value) > 0) {
                inOrder = false;
            }
        }
        for(int i = retIndex + 1; inOrder && i < endIndex; i++) {
            if(list.get(i).compareTo(value) < 0) {
                inOrder = false;
            }
        }
        return inOrder;
    }

    @Test
    void sortTest() {
        isSortedListEqual(list, new ArrayList<>(list));
        isSortedListEqual(list2, new ArrayList<>(list2));
        isSortedListEqual(list3, new ArrayList<>(list3));
        isSortedListEqual(alreadySortedList, new ArrayList<>(alreadySortedList));
        isSortedListEqual(nearlySortedList, new ArrayList<>(nearlySortedList));
        isSortedListEqual(reverseSortedList, new ArrayList<>(reverseSortedList));
    }

    private void isSortedListEqual(List<Integer> list1, List<Integer> list2) {
        Collections.sort(list1);
        SmallerBiggerSort.sort(list2);
        assertArrayEquals(list1.toArray(), list2.toArray());
    }

    @Test
    void benchmarkSorts() {
        final int listSize = 100;
        System.out.println("Random List:");
        runBenchmark(createList(listSize, "random"));
        System.out.println("Already Sorted List");
        runBenchmark(createList(listSize, "sorted"));
        System.out.println("Nearly Sorted List");
        runBenchmark(createList(listSize, "nearlySorted"));
        System.out.println("Reverse Sorted List");
        runBenchmark(createList(listSize, "reverseSorted"));
    }

    private void runBenchmark(List<Integer> list) {
        System.out.println("List Before: " + list);
        long startTime = System.nanoTime();
        SmallerBiggerSort.sort(list);
        long endTime = System.nanoTime();
        System.out.println("List After: " + list);
        System.out.println("Time to Sort: " + (endTime - startTime) + " ns\n");
    }

    private List<Integer> createList(int size, String type) {
        List<Integer> list = new ArrayList<>();
        Random generator = new Random();
        if(type.equals("reverseSorted")) {
            for(int i = size; i > 0; i--) {
                list.add(i);
            }
        } else if(type.equals("nearlySorted")) {
            final int tensPlace = 10;
            for(int i = 0; i < size; i++) {
                if(i % tensPlace == 0) {
                    list.add(i + list.size());
                } else {
                    list.add(i);
                }
            }
        } else if(type.equals("sorted")) {
            for(int i = 0; i < size; i++) {
                list.add(i);
            }
        } else {
            for(int i = 0; i < size; i++) {
                list.add(generator.nextInt(size) + 1);
            }
        }
        return list;
    }
}
