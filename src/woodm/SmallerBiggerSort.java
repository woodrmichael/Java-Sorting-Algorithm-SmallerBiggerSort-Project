/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 12 - Smaller Bigger Sort
 * Name: Michael Wood
 * Created: 4/11/2024
 */
package woodm;

import java.util.List;

/**
 * A Smaller Bigger Sort Class is a class that contains methods used to sort data in O(n) time.
 */
public class SmallerBiggerSort {
    /**
     * Sorts the list in O(n) time. Places all elements smaller than the element at startInclusive
     * before startInclusive and all the elements larger after startInclusive.
     * @param list the list to sort.
     * @param startInclusive index where the list should start.
     * @param endExclusive index where the list should end.
     * @param <T> A reference type that is Comparable.
     * @return index where the first element ended up being placed.
     */
    public static <T extends Comparable<T>> int smallerBigger(
            List<T> list, int startInclusive, int endExclusive) {
        return 0;
    }

    /**
     * A recursive sort method that works by creating sublists of all the elements
     * smaller than a particular value and larger than the value.
     * @param list the list to sort.
     * @param startInclusive index where the list should start.
     * @param endExclusive index where the list should end.
     * @param <T> A reference type that is Comparable.
     */
    public static <T extends Comparable<T>> void sort(
            List<T> list, int startInclusive, int endExclusive) {

    }

    /**
     * Sorts the list by calling the recursive sort() method.
     * @param list the list to sort.
     * @param <T> A reference type that is Comparable.
     */
    public static <T extends Comparable<T>> void sort(List<T> list) {
        sort(list, 0, list.size() - 1);
    }
}
