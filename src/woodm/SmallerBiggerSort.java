/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 12 - Smaller Bigger Sort
 * Name: Michael Wood
 * Created: 4/11/2024
 */
package woodm;

import java.util.ArrayList;
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
     *
     * @throws IllegalArgumentException thrown if the list is null or
     * the startInclusive or endExclusive values aren't valid.
     */
    public static <T extends Comparable<T>> int smallerBigger(
            List<T> list, int startInclusive, int endExclusive) throws IllegalArgumentException {
        if(list == null || startInclusive < 0 || startInclusive >= endExclusive
                || endExclusive > list.size()) {
            throw new IllegalArgumentException();
        }
        T first = list.get(startInclusive);
        List<T> smaller = new ArrayList<>();
        List<T> bigger = new ArrayList<>();
        for(int i = 0; i < startInclusive; i++) {
            smaller.add(list.get(i));
        }
        for(int i = startInclusive + 1; i < endExclusive; i++) {
            if(list.get(i).compareTo(first) <= 0) {
                smaller.add(list.get(i));
            } else if (list.get(i).compareTo(first) > 0) {
                bigger.add(list.get(i));
            }
        }
        smaller.add(first);
        int index = smaller.size() - 1;
        for(int i = endExclusive; i < list.size(); i++) {
            bigger.add(list.get(i));
        }
        list.clear();
        list.addAll(smaller);
        list.addAll(bigger);
        return index;
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
        if(startInclusive != endExclusive) {
            int index = smallerBigger(list, startInclusive, endExclusive);
            sort(list, startInclusive, index);
            sort(list, index + 1, endExclusive);
        }
    }

    /**
     * Sorts the list by calling the recursive sort() method.
     * @param list the list to sort.
     * @param <T> A reference type that is Comparable.
     */
    public static <T extends Comparable<T>> void sort(List<T> list) {
        sort(list, 0, list.size());
    }
}
